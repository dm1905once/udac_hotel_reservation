package api;

import model.Customer;
import model.iRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    // Limit class to a singleton instance
    private static AdminResource instance;
    private AdminResource(){};
    public static AdminResource getInstance(){
        if (instance == null){
            instance = new AdminResource();
        }
        return instance;
    }

    public Customer getCustomer(String email){
        CustomerService csInstance = CustomerService.getInstance();
        return csInstance.getCustomer(email);
    }

    public void addRoom(List<iRoom> rooms){
        ReservationService rsInstance = ReservationService.getInstance();
        for (iRoom room : rooms){
            rsInstance.addRoom(room);
        }
    }

    public Collection<iRoom> getAllRooms(){
        ReservationService rsInstance = ReservationService.getInstance();
        return rsInstance.getRooms();
    }

    public Collection<Customer> getAllCustomers(){
        CustomerService csInstance = CustomerService.getInstance();
        return csInstance.getCustomers();
    }

    public void displayAllReservations(){
        ReservationService rsInstance = ReservationService.getInstance();
        rsInstance.printAllReservations();
    }
}
