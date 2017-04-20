package bsdb.itu.dk.guestlist.model;

import java.text.SimpleDateFormat;
import java.util.Date;

// Simpel gæst klasse
public class Guest {

    private int id;
    private String name;
    private Stay stay;

    public Guest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSimpleDate() {
        return SimpleDateFormat.getDateInstance().format(stay.getFrom());
    }
}
