package model;

import java.util.Date;

public class Reservation {
    protected Customer customer;
    protected iRoom room;
    protected Date checkInDate;
    protected Date checkOutDate;

    public Reservation(Customer customer, iRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return "RESERVATION - Customer: " + customer.getName() + " Room: " + room.getRoomNumber() + " Dates: " + checkInDate + " through " + checkOutDate;
    }
}
