package bsdb.itu.dk.guestlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends Activity {

    private List<Guest> guests;
    private List<Guest> filteredList;

    public static final String GUEST_ID = "bsdb.itu.dk.guestlist.guestid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        GuestStore.initialize();
        guests = GuestStore.getAll();
        filteredList = new ArrayList<Guest>();
        filteredList.addAll(guests);

        ListView guestListView = (ListView) findViewById(R.id.guest_list);
        final GuestListAdapter adapter = new GuestListAdapter();

        guestListView.setAdapter(adapter);

        final EditText searchField = (EditText) findViewById(R.id.search_field);
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

        guestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest g = guests.get(position);

                Intent i = new Intent(MainActivity.this, GuestActivity.class);
                i.putExtra(GUEST_ID, g.getId());

                startActivity(i);
            }
        });

    }


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
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                view = inflater.inflate(R.layout.guest_list_item, null);
            }

            TextView guestName = (TextView) view.findViewById(R.id.guest_name);
            TextView arrival = (TextView) view.findViewById(R.id.guest_arrival);

            Guest g = filteredList.get(position);

            if(g != null) {
                guestName.setText(g.getName());
                arrival.setText(g.getSimpleDate());
            }

            return view;
        }
    }
}
