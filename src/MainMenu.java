import api.HotelResource;
import model.Customer;
import model.Reservation;
import model.iRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {

    public static void main(String[] args) {
        String mainMenuSelectedOption = null;
        Scanner scanner = new Scanner(System.in);
        do {
            printMainMenu();
            try {
                mainMenuSelectedOption = scanner.next();
                int input = Integer.parseInt(mainMenuSelectedOption);
                if (input < 0 || input > 5) {
                    throw new Exception();
                } else {
                    switch (input) {
                        case 1:
                            handleReserveRoom(); break;
                        case 3:
                            handleCreateAccount(); break;
                        case 4:
                            new AdminMenu();
                    }
                }
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        } while (!mainMenuSelectedOption.equals("5"));
    }

    private static void printMainMenu(){
        String mainMenu = """
                 ==========
                 Welcome to the Hotel Reservation Application
                 1. Find and reserve a room
                 2. See my reservations
                 3. Create an account
                 4. Admin
                 5. Exit
                 ==========
                 Please select a number for the menu option""";
        System.out.println(mainMenu);
    }

    private static boolean accountExists(String email){
        HotelResource hrInstance = HotelResource.getInstance();
        Customer customer = hrInstance.getCustomer(email);
        return customer != null;
    }

    private static boolean isValidDateFormat(String date){
        String regexDateFormat = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern dateValidationPattern = Pattern.compile(regexDateFormat);
        return dateValidationPattern.matcher(date).matches();
    }

    private static void handleCreateAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email (format: name@domain.com)");
        String email = scanner.next();
        System.out.println("First Name");
        String first = scanner.next();
        System.out.println("Last Name");
        String last = scanner.next();

        HotelResource hrInstance = HotelResource.getInstance();
        hrInstance.createACustomer(email, first, last);
    }

    private static void handleReserveRoom(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter CHECK-IN date (format: mm/dd/yyyy)");
        String checkinString = scanner.next();
        System.out.println("Enter CHECK-OUT date (format: mm/dd/yyyy)");
        String checkoutString = scanner.next();

        try {
            HotelResource hrInstance = HotelResource.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date checkInDate = formatter.parse(checkinString);
            Date checkOutDate = formatter.parse(checkoutString);
            List<iRoom> rooms = new ArrayList<iRoom>(hrInstance.findARoom(checkInDate, checkOutDate));
            if (rooms.isEmpty()) {
                System.out.println("No rooms available for the given dates");
            } else {
                System.out.println("Would you like to book a room? (y/n");
                String proceedToBook = scanner.next();
                if (proceedToBook.toLowerCase().equals("y")){
                    System.out.println("Do you have an account with us? (y/n");
                    String hasAccount = scanner.next();
                    if (!hasAccount.toLowerCase().equals("y")){
                        System.out.println("Please create an account first (option 3");
                    } else {
                        System.out.println("Enter your email (format name@domain.com");
                        String email = scanner.next();
                        if (!accountExists(email)){
                            System.out.println("The account you entered does not exist. Please create an account first");
                        } else {
                            System.out.println("What room would you like to reserve?");
                            String selectedRoom = scanner.next();
                            iRoom room = hrInstance.getRoom(selectedRoom);
                            if (!rooms.contains(room)){
                                System.out.println("Please select a room from the list of available rooms");
                            } else {
                                Reservation reservation = hrInstance.bookARoom(email, room, checkInDate, checkOutDate);
                                System.out.println(reservation);
                            }
                        }
                    }
                }
            }

        } catch (ParseException pe){
            System.out.println("Invalid date(s). Please try again with valid dates");
        }


    }


}
