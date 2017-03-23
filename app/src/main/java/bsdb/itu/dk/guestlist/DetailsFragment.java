package bsdb.itu.dk.guestlist;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            int guestId = getArguments().getInt(MainActivity.GUEST_ID);
            updateGuest(guestId);
        }
    }

    // Opdaterer text feltet med gæste navn
    public void updateGuest(int guestId) {
        TextView textView = (TextView) getActivity().findViewById(R.id.guest_activity_name);
        Guest g = GuestStore.getGuest(guestId);

        if(textView != null && g != null) {
            textView.setText(g.getName());
        }
    }
}
