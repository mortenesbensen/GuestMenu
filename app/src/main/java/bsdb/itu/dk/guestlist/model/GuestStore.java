package bsdb.itu.dk.guestlist.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bsdb.itu.dk.guestlist.model.Guest;


// Simple datastore over gæster, stays og værelser
// OBS: Denne datastruktur ligner ikke den fra kursusbogen, men er noget mere simpel!
public class GuestStore {

    private static List<Guest> guests = new ArrayList<Guest>();
    private static List<Integer> rooms = new ArrayList<>();

    public static void initialize() {

        Calendar cal = Calendar.getInstance();;
        Date now = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = cal.getTime();

        // Dummy data
        Guest g1 = new Guest(1, "Morten Esbensen");
        Guest g2 = new Guest(2, "Lisa Simpson");
        Guest g3 = new Guest(3, "Barack Obama");

        int room1 = 1;
        int room2 = 2;
        int room3 = 3;

        Stay s1 = new Stay(now, tomorrow, room1, Stay.StayState.BOOKED);
        Stay s2 = new Stay(now, tomorrow, room2, Stay.StayState.CHECKED_IN);
        Stay s3 = new Stay(now, tomorrow, room3, Stay.StayState.BOOKED);

        g1.setStay(s1);
        g2.setStay(s2);
        g3.setStay(s3);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        guests.add(g1);
        guests.add(g2);
        guests.add(g3);

    }

    public static List<Guest> getAll() {
        return guests;
    }

    public static List<Integer> getAllRooms() { return rooms; }

    public static Guest getGuest(int id) {
        for(Guest g : guests) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }



    public static void deleteGuest(int guestId) {
        for(int i = guests.size() -1; i >= 0; i--) {
            if(guests.get(i).getId() == guestId) {
                guests.remove(i);
                return;
            }
        }
    }
}
