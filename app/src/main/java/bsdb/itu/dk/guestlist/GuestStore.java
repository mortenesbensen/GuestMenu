package bsdb.itu.dk.guestlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Simple datastore
public class GuestStore {

    private static List<Guest> guests = new ArrayList<Guest>();

    public static void initialize() {

        // Dummy gæster
        Guest g1 = new Guest(1, "Morten Esbensen", new Date());
        Guest g2 = new Guest(2, "Lisa Simpson", new Date());
        Guest g3 = new Guest(3, "Barack Obama", new Date());

        guests.add(g1);
        guests.add(g2);
        guests.add(g3);

    }

    public static List<Guest> getAll() {
        return guests;
    }

    public static Guest getGuest(int id) {
        for(Guest g : guests) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }
}
