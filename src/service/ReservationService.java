package service;

import model.Customer;
import model.Reservation;
import model.iRoom;

import java.util.*;

public class ReservationService {

    // Limit class to a singleton instance
    private static ReservationService instance;
    private ReservationService(){};
    public static ReservationService getInstance(){
        if (instance == null){
            instance = new ReservationService();
        }
        return instance;
    }

    // Global collections
    Collection<Reservation> reservations = new ArrayList<>(){};
    Map<String, iRoom> rooms = new HashMap<>();

    // Methods
    // default Method
    Collection<Reservation> getReservationsByRoom(String roomID){
        Collection<Reservation> reservationsByRoom = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomID) {
                reservationsByRoom.add(reservation);
            }
        }
        return reservationsByRoom;
    }

    public void addRoom(iRoom room){
        String roomNumber = room.getRoomNumber();
        if (rooms.containsKey(roomNumber)){
            System.out.println("ERROR: Room ID " + roomNumber + " already exists. It will not be added.");
        } else {
            rooms.put(roomNumber, room);
        }
    };

    public iRoom getARoom(String roomId){
        iRoom room = rooms.get(roomId);
        if (room == null) {
            System.out.println("ERROR: Room ID " + roomId + "does not exist");
        }
        return room;
    }

    public Reservation reserveARoom(Customer customer, iRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(newReservation);
        return newReservation;
    }

    public Collection<iRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<iRoom> availableRooms = new ArrayList<iRoom>();
        Collection<Reservation> reservationsByRoom = new ArrayList<>();

        // Iterate through every room
        for (String roomID : rooms.keySet()){
            // Add reservations made for each room into a temp list
            reservationsByRoom = getReservationsByRoom(roomID);

                // This room has not been reserved.
                if (reservationsByRoom.size() == 0) {
                    availableRooms.add(getARoom(roomID));
                } else {
                    // Room has at least one reservation. Look for available dates
                    for (Reservation reservation : reservationsByRoom) {
                        Date reservationStarts = reservation.getCheckInDate();
                        Date reservationEnds = reservation.getCheckOutDate();
                        if (checkInDate.after(reservationEnds) || checkOutDate.before(reservationStarts)){
                            availableRooms.add(reservation.getRoom());
                        }
                    }
                }
        }
        return availableRooms;
    }


    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> reservationsByCustomer = new ArrayList<>();
        int thisCustomerHashCode = customer.hashCode();
        for (Reservation reservation : reservations){
            if (reservation.getCustomer().hashCode() == thisCustomerHashCode){
                reservationsByCustomer.add(reservation);
            }
        }
        return reservationsByCustomer;
    }

    public void printAllReservations(){
        System.out.println("Printing all reservations...");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public Collection<iRoom> getRooms(){
        return rooms.values();
    }

}
