import java.util.Scanner;

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
                }
            } catch (Exception ex){
                System.out.println("Please choose one of the options");
            }
        } while (!mainMenuSelectedOption.equals("5"));
    }

    private static void printMainMenu(){
        String mainMenu = """
                 1. Find and reserve a room
                 2. See my reservations
                 3. Create an account
                 4. Admin
                 5. Exit""";
        System.out.println(mainMenu);
    }
}
