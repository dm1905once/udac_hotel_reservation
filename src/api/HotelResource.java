package api;

import model.Customer;
import model.Reservation;
import model.iRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    // Limit class to a singleton instance
    private static HotelResource instance;
    private HotelResource(){};
    public static HotelResource getInstance(){
        if (instance == null){
            instance = new HotelResource();
        }
        return instance;
    }

    public Customer getCustomer(String email){
        // Necesito jalar la instancia?
        CustomerService csInstance = CustomerService.getInstance();
        return csInstance.getCustomer(email);

        // o esto es suficiente?
        // return service.CustomerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        CustomerService csInstance = CustomerService.getInstance();
        csInstance.addCustomer(email, firstName, lastName);
    }

    public iRoom getRoom(String roomNumber){
        ReservationService rsInstance = ReservationService.getInstance();
        return rsInstance.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, iRoom room, Date checkInDate, Date checkOutDate){
        CustomerService csInstance = CustomerService.getInstance();
        ReservationService rsInstance = ReservationService.getInstance();

        return rsInstance.reserveARoom(csInstance.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail){
        CustomerService csInstance = CustomerService.getInstance();
        ReservationService rsInstance = ReservationService.getInstance();

        return rsInstance.getCustomerReservation(csInstance.getCustomer(customerEmail));
    }

    public Collection<iRoom> findARoom(Date checkIn, Date checkOut){
        ReservationService rsInstance = ReservationService.getInstance();
        return rsInstance.findRooms(checkIn, checkOut);
    }

}
