package bsdb.itu.dk.guestlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import bsdb.itu.dk.guestlist.model.GuestStore;

public class BreakfastActivity extends Activity {

    // En liste af værelser (integers for nu)
    private List<Integer> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        rooms = GuestStore.getAllRooms();

        // Sætter en adapter på vores ListView
        RoomListAdapter adapter = new RoomListAdapter();
        ListView breakfastList = (ListView) findViewById(R.id.breakfast_list);
        breakfastList.setAdapter(adapter);
    }

    // Liste adapter
    class RoomListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rooms.size();
        }

        @Override
        public Object getItem(int position) {
            return rooms.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(view == null) {
                LayoutInflater inflater = LayoutInflater.from(BreakfastActivity.this);
                view = inflater.inflate(R.layout.breakfast_list_item, null);
            }

            TextView roomNumber = (TextView) view.findViewById(R.id.room_number);
            roomNumber.setText("" + rooms.get(position));

            return view;
        }
    }
}
