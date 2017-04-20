package bsdb.itu.dk.guestlist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class MainActivity extends Activity implements GuestListFragment.GuestListItemClicked {

    // Key til vores guest id
    public static final String GUEST_ID = "bsdb.itu.dk.guestlist.guestid";

    // Holder styr på om vi har adgang til en eller to fragments
    private boolean isDualPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        isDualPanel = findViewById(R.id.fragment_container) == null;

        // Hvis vi ikke har adgang til 2 fragments skal vi tilføje en fragment til vores container
        if(!isDualPanel) {

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            GuestListFragment fragment = new GuestListFragment();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();

        }
    }

    @Override
    public void onGuestListItemClick(int guestId) {

        // Hvis vi har adagng til vores detalje fragment kan vi nøjes med at opdatere navnet. Hvis ikke skal vi lave en ny
        // fragment og tilføje den i stedet for vores liste
        if(isDualPanel) {
            DetailsFragment fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
            fragment.updateGuest(guestId);
        } else {
            DetailsFragment fragment = new DetailsFragment();
            Bundle b = new Bundle();
            b.putInt(GUEST_ID, guestId);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragment.setArguments(b);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
