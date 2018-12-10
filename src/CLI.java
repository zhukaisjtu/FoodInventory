import java.util.List;
import java.util.Scanner;

public class CLI {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        System.exit(0);
    }

    // main menu
    private static void menu() {
        whileloop: while (true) {
            options();
            String action = action();
//            System.out.println(action);
            switch (action) {
                case "0":
                    System.out.println("Successfully exited! Have a good day!");
                    break whileloop;
                case "1.1":
                    addFood();
                    break;
                case "1.2":
                    updateFood();
                    break;
                case "1.3":
                    deleteFood();
                    break;
                default:
                    System.out.println("Please enter a valid action! \n");
            }
        }
    }

    // Display options for user to choose from
    private static void options() {
        System.out.println("1 Food Operations \n" +
                "   1.1 Add Food \n" +
                "   1.2 Update Food \n" +
                "   1.3 Delete Food \n" +
//                "2 Menu Operations \n" +
//                "   2.1 Add Menu \n" +
//                "   2.2 Modify Menu \n" +
//                "   2.3 Delete Menu \n" +
                "0 Quit \n");
    }

    // select an action
    private static String action() {
        System.out.println("Please enter your intended action: ");
        String action = scanner.nextLine();
        System.out.println();
        return action;
    }

    // Add Food
    private static void addFood() {
        System.out.println("Adding food...");

        // food name
        String foodName = nameScanner();

        // food quantity
        int quantity = numberScanner("Enter food quantity: ", 1);

        // days before expire
        int daysBeforeExpire = numberScanner("Enter days before expiration: ", 1);

        Food food = new Food();
        food.setFoodName(foodName);
        food.setNumberOfUnits(quantity);
        food.setDaysBeforeExpire(daysBeforeExpire);

        FoodManager foodManager = new FoodManager();
        foodManager.addFood(food);
        System.out.println("Food added! \n");
    }

    // Update Food
    private static void updateFood() {
        System.out.println("Updating food...");

        // food name
        String foodName = nameInput();

        // Store all food
        FoodManager foodManager = new FoodManager();
        List<Food> list = foodManager.searchFoodByName(foodName);

        // display all food
        displayAllFood(list);

        // select a food
        int foodToDelete = selectFromList(list, false);

        // Update a food
        updateSelectedFood(list, foodToDelete);

    }

    private static void updateSelectedFood(List<Food> list, int num) {
        if (list.size() > 0) {
            Food food = list.get(num - 1);
            System.out.println(food);

            // food quantity
            int quantity = numberScanner("Enter food quantity: ", 1);

            // days before expire
            int daysBeforeExpire = numberScanner("Enter days before expiration: ", 1);

            food.setNumberOfUnits(quantity);
            food.setDaysBeforeExpire(daysBeforeExpire);

            FoodManager foodManager = new FoodManager();
            foodManager.updateFood(food);

            System.out.println("Food Updated! \n");
        }
    }

    // return a valid number
    private static int numberScanner(String str, int limit) {
        int number;
        while (true) {
            number = numberInput(str);
            if (number < limit) {
                System.out.println("Number should be greater than or equal to " + limit);
            } else {
                break;
            }
        }
        return number;
    }

    private static int numberInput(String str) {
        int number = 0;
        while (true) {
            System.out.println(str);
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please input a valid NUMBER!");
            }
        }
        return number;
    }

    // Read in Name
    private static String nameScanner() {

        String foodName;

        nameScannerWhileLoop:
        while (true) {
            // Input name
            foodName = nameInput();

            // Check whether name in DB
            boolean b = isFoodNameInDB(foodName);

            if (b) {
                break nameScannerWhileLoop;
            } else {
                System.out.println("Food name DOES NOT exist in DB.");
                innerloop:
                while (true) {
                    System.out.println("If this is a new food, please enter 1. \n" +
                            "If you want to modify the food name, please enter 2.");
                    String option = scanner.nextLine();
                    switch (option) {
                        case "1": {
                            break nameScannerWhileLoop;
                        }
                        case "2": {
                            foodName = nameScanner();
                            break nameScannerWhileLoop;
                        }
                        default: {
                            System.out.println("Please enter 1 or 2!");
                            break;
                        }
                    }
                }
            }
        }
        return foodName;
    }

    // Return all food names in db
    private static boolean isFoodNameInDB(String foodName) {
        FoodManager foodManager = new FoodManager();
        return foodManager.isFoodNameInDB(foodName);
    }

    // Input a name
    private static String nameInput() {
        String foodName;
        while (true) {
            System.out.println("Please enter the food name: ");
            foodName = scanner.nextLine();
            if (foodName.isEmpty()) {
                System.out.println("Please enter a valid name!");
            } else {
                foodName = foodName.toLowerCase();
                break;
            }
        }
        return foodName;
    }

    private static void displayAllFood(List<Food> list) {
        if (list.size() == 0) {
            System.out.println("There is no such food! \n");
        } else {
            int i = 1;
            for (Food f : list) {
                System.out.println(i + ": " + f);
                i++;
            }
        }
    }

    // select a food from a list
    private static int selectFromList(List<Food> list, boolean all) {
        // when all == true, you can select either one food or all foods.
        // when all == false, you can only select one food.

        // select food to delete
        int num = -1;
        if (list.size() > 0) {
            while (true) {
                if (all) {
                    num = numberScanner("please select the number of item. Enter 0 to select all.", 0);
                    if ((num >= 0) && (num <= list.size())) {
                        break;
                    } else {
                        System.out.println("Please input a valid number!");
                    }
                } else {
                    num = numberScanner("please select the number of item.", 1);
                    if ((num >= 1) && (num <= list.size())) {
                        break;
                    } else {
                        System.out.println("Please input a valid number!");
                    }
                }
            }
        }
        return num;
    }

    // delete the selected food
    private static void deleteFromList(List<Food> list, int num) {
        if (num >= 0) {
            FoodManager foodManager = new FoodManager();
            // delete selected food
            if (num == 0) {
                for (Food f : list) {
                    foodManager.deleteFood(f);
                }
            } else {
                foodManager.deleteFood(list.get(num - 1));
            }
            System.out.println("Deleted! \n");
        }
    }

    // Delete Food
    private static void deleteFood() {
        System.out.println("deleting food...");

        // food name
        String foodName = nameInput();

        // Store all food
        FoodManager foodManager = new FoodManager();
        List<Food> list = foodManager.searchFoodByName(foodName);

        // display all food
        displayAllFood(list);

        // select a food
        int foodToDelete = selectFromList(list, true);

        // delete the selected food
        deleteFromList(list, foodToDelete);
    }
}