package bsdb.itu.dk.guestlist;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bsdb.itu.dk.guestlist.model.Guest;
import bsdb.itu.dk.guestlist.model.GuestStore;
import bsdb.itu.dk.guestlist.model.Stay;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuestListFragment extends Fragment {

    // Interface til at kommunikere mellem aktivitet og fragment.
    public interface GuestListItemClicked {
        public void onGuestListItemClick(int guestId);
    }

    // Vores aktivitet som imlpementerer GuestListItemClicked
    private GuestListItemClicked guestListListener;

    // Liste af alle gæster og en filtreret liste der vises i vores ListView
    private List<Guest> guests;
    private List<Guest> filteredList;

    // Adapter til vores liste
    private GuestListAdapter adapter;

    public GuestListFragment() {

        GuestStore.initialize();
        guests = GuestStore.getAll();
        filteredList = new ArrayList<Guest>();
        filteredList.addAll(guests);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guest_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Når dette fragment placeres i en aktivitet, tilføjer vi aktiviteten som lytter,
        // så vi kan fortælle når en bruger klikker på en gæst i listen
        guestListListener = (GuestListItemClicked) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Sæt en adapter på vores liste af gæster
        ListView guestListView = (ListView) getActivity().findViewById(R.id.guest_list);
        adapter = new GuestListAdapter();
        guestListView.setAdapter(adapter);

        // Live søgning på gæst
        final EditText searchField = (EditText) getActivity().findViewById(R.id.search_field);
        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                filteredList.clear();
                String searchString = searchField.getText().toString().toLowerCase();

                for(Guest g : guests) {
                    if(g.getName().toLowerCase().contains(searchString)) {
                        filteredList.add(g);
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });

        // Lyt på når en bruger klikker på en gæst i listen
        guestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest g = filteredList.get(position);
                guestListListener.onGuestListItemClick(g.getId());
            }
        });
    }

    // Liste adapter for vores gæste liste
    class GuestListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public Object getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(view == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                view = inflater.inflate(R.layout.guest_list_item, null);
            }

            TextView guestName = (TextView) view.findViewById(R.id.guest_name);
            TextView arrival = (TextView) view.findViewById(R.id.guest_arrival);

            Guest g = filteredList.get(position);

            if(g != null) {
                guestName.setText(g.getName());
                if(g.getStay().getState() == Stay.StayState.BOOKED) {
                    arrival.setText("Arrives on " + g.getSimpleDate());
                } else if(g.getStay().getState() == Stay.StayState.CHECKED_IN) {
                    arrival.setText("Checked in");
                }

            }
            return view;
        }
    }
}
