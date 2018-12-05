import java.util.Scanner;



public class CLI {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    // main menu
    private static void menu() {
        boolean flag = true;
        whileloop: while (flag) {
            options();
            String action = action();
//            System.out.println(action);
            switch (action) {
                case "0":
                    flag = false;
                    System.out.println("Successfully exited! Have a good day!");
                    break;
                case "1.1":
                    addFood();
                    break;
                case "1.2":
                    deleteFood();
                    break;
                default:
                    System.out.println("Please enter a valid action!");

            }
        }

    }

    // Display options for user to choose from
    private static void options() {
        System.out.println("1 Food Operations \n" +
                "   1.1 Add Food \n" +
                "   1.2 Delete Food \n" +
                "0 Quit \n");
    }

    // select an action
    private static String action() {
        System.out.println("Please enter your intended action: ");
        String action = scanner.nextLine();
        return action;
    }

    // Add Food
    private static void addFood() {
        System.out.println("Adding food...");

        System.out.println("Enter food name: ");
        String foodName = scanner.nextLine(); // <------------------ Check food name

        System.out.println("Enter food quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter days before expiration: ");
        int daysBeforeExpire = Integer.parseInt(scanner.nextLine());

        Food food = new Food();
        food.setFoodName(foodName);
        food.setNumberOfUnits(quantity);
        food.setDaysBeforeExpire(daysBeforeExpire);

        FoodManager foodManager = new FoodManager();
        foodManager.addFood(food);
    }

    // Delete Food
    private static void deleteFood() {
        System.out.println("deleting food...");
    }
}
