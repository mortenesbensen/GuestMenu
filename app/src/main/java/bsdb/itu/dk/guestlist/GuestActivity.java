package bsdb.itu.dk.guestlist;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class GuestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        TextView guestName = (TextView) findViewById(R.id.guest_activity_name);

        int guestid = getIntent().getIntExtra(MainActivity.GUEST_ID, 0);

        Guest g = GuestStore.getGuest(guestid);

        guestName.setText(g.getName());

    }

}
