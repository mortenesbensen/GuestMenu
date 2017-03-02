package bsdb.itu.dk.guestlist;

import java.text.SimpleDateFormat;
import java.util.Date;

// Simpel gæst klasse
public class Guest {

    private int id;
    private String name;
    private Date arrival;

    public Guest(int id, String name, Date arrival) {
        this.id = id;
        this.name = name;
        this.arrival = arrival;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSimpleDate() {
        return SimpleDateFormat.getDateInstance().format(arrival);
    }
}
