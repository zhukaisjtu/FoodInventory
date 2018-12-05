import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}