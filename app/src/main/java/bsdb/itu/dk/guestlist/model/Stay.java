package bsdb.itu.dk.guestlist.model;

import java.util.Date;

/**
 * Created by Morten on 18/04/2017.
 *
 * Note: For simplicity reasons, this model differs from the example in the course book
 */

public class Stay {

    private Date from;
    private Date to;
    private int room;
    private StayState state;

    public Stay(Date from, Date to,  int room, StayState state) {
        this.from = from;
        this.to = to;
        this.room = room;
        this.state = state;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public int getRoom() {
        return room;
    }

    public StayState getState() {
        return state;
    }

    public void setState(StayState state) {
        this.state = state;
    }

    public enum StayState {
        BOOKED, CHECKED_IN
    }

}
