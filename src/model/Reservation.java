package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private iRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, iRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public iRoom getRoom() {
        return room;
    }

    public void setRoom(iRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString(){
        return "RESERVATION - Customer: " + customer.getName() + " Room: " + room.getRoomNumber() + " Dates: " + checkInDate + " through " + checkOutDate;
    }
}
