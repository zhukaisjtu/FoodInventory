import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodManagerTest {

    @Test
    void addFood() {
        Food food = new Food();
        food.setFoodName("Tomato");
        food.setNumberOfUnits(2);
        food.setDaysBeforeExpire(5);
        FoodManager foodManager = new FoodManager();
        Assertions.assertTrue(foodManager.addFood(food));
    }


    @Test
    void deleteFood() {
        Food food = new Food();
        food.setFoodId(1);
        food.setFoodName("Tomato");
        food.setNumberOfUnits(2);
        food.setDaysBeforeExpire(5);
        FoodManager foodManager = new FoodManager();
        Assertions.assertTrue(foodManager.deleteFood(food));
    }

    @Test
    void searchFoodByName() {
        Food food = new Food();
        food.setFoodId(1);
        food.setFoodName("tomato");
        food.setNumberOfUnits(5);
        food.setDaysBeforeExpire(2);

        FoodManager foodManager = new FoodManager();
        Assertions.assertEquals(food, foodManager.searchFoodByName("tomato").get(0));
    }

    @Test
    void isFoodNameInDB() {
        FoodManager foodManager = new FoodManager();
        Assertions.assertTrue(foodManager.isFoodNameInDB("tomato"));
    }

    @Test
    void isFoodNameInDBwithNull() {
        FoodManager foodManager = new FoodManager();
        Assertions.assertFalse(foodManager.isFoodNameInDB(""));
    }

    @Test
    void updateFood() {
        Food food = new Food();
        food.setFoodId(2);
        food.setFoodName("tomato");
        food.setNumberOfUnits(5);
        food.setDaysBeforeExpire(2);

        FoodManager foodManager = new FoodManager();
        Assertions.assertTrue(foodManager.updateFood(food));
    }
}