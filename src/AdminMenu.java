import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.Room;
import model.RoomType;
import model.iRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public AdminMenu(){
        String adminMenuSelectedOption = null;
        Scanner scanner = new Scanner(System.in);
        do {
            printAdminMenu();
            try {
                adminMenuSelectedOption = scanner.next();
                int input = Integer.parseInt(adminMenuSelectedOption);
                if (input < 0 || input > 6) {
                    throw new Exception();
                } else {
                    handleMenuOption(input);
                }
            } catch (Exception ex){
                System.out.println(ex.getLocalizedMessage());
                System.out.println("Please choose one of the options");
            }
        } while (!adminMenuSelectedOption.equals("6"));
    }

    private static void printAdminMenu(){
        String adminMenu = """
                 ==========
                 Admin Menu
                 1. See all Customers
                 2. See all Rooms
                 3. See all Reservations
                 4. Add a Room
                 5. Add test data
                 6. Back to Main Menu
                 ==========
                 Please select a number for the menu option""";
        System.out.println(adminMenu);
    }

    private static void handleMenuOption(int option){
        switch (option) {
            case 1: // See all Customers
                List<Customer> customers = new ArrayList<Customer>(AdminResource.getInstance().getAllCustomers());
                if (customers.size() == 0 ){
                    System.out.println("This hotel does not have any customers yet");
                } else {
                    System.out.println("Displaying all Customers");
                    for (Customer customer : customers){
                        System.out.println(customer);
                    }
                }
                break;
            case 2: // See all Rooms
                List<iRoom> rooms = new ArrayList<iRoom>(AdminResource.getInstance().getAllRooms());
                if (rooms.size() == 0 ){
                    System.out.println("This hotel does not have any rooms yet");
                } else {
                    System.out.println("Displaying all Rooms");
                    for (iRoom room : rooms){
                        System.out.println(room);
                    }
                }
                break;
            case 3: // See all Reservations
                AdminResource.getInstance().displayAllReservations();
                break;
            case 4: // Add a Room
                ArrayList<iRoom> roomsToBeAdded = new ArrayList<iRoom>();
                String addMoreRooms;
                Scanner scanner = new Scanner(System.in);
                do {
                    Room newRoom = handleAddRoom();
                    if (newRoom != null) roomsToBeAdded.add(newRoom);
                    do {
                        System.out.println("Would you like to add another room? (y/n)");
                        addMoreRooms = scanner.next();
                    } while (!addMoreRooms.toLowerCase().equals("y") && !addMoreRooms.toLowerCase().equals("n"));
                } while (addMoreRooms.toLowerCase().equals("y"));
                AdminResource.getInstance().addRoom(roomsToBeAdded);
                break;
            case 5: // Add test data
                HotelResource hrInstance = HotelResource.getInstance();
                AdminResource arInstance = AdminResource.getInstance();

                List<iRoom> testRooms = new ArrayList<iRoom>();
                testRooms.add(new Room("100", 100, RoomType.SINGLE));
                testRooms.add(new Room("200", 200, RoomType.DOUBLE));
                arInstance.addRoom(testRooms);

                hrInstance.createACustomer("daniel1@nada.com", "Daniel", "X");
                hrInstance.createACustomer("daniel2@nada.com", "Daniel", "Y");

                break;
        }
    }

    private static Room handleAddRoom(){
        Scanner scanner = new Scanner(System.in);
        String strRoomNumber = "";
        Double dblPricePerNight = 0.0;
        int intRoomType =0;
        try {
            System.out.println("Enter room number");
            strRoomNumber = scanner.next();
            System.out.println("Enter price per night");
            String strPricePerNight = scanner.next();
            dblPricePerNight = Double.parseDouble(strPricePerNight);
            System.out.println("Enter room type (1: Single, 2: Double)");
            String strRoomType = scanner.next();
            intRoomType = Integer.parseInt(strRoomType);
            if (dblPricePerNight < 0 ) {
                throw new Exception("Invalid value entered");
            }
            if ((intRoomType  != 1 && intRoomType != 2) ) {
                throw new Exception("Please enter 1 or 2");
            }
            Room newRoom = new Room(strRoomNumber, dblPricePerNight, intRoomType==1? RoomType.SINGLE:RoomType.DOUBLE);
            System.out.println(newRoom);
            return newRoom;
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
