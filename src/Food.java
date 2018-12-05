import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Food {

    @Id
    private int foodId;
    private String foodName;
    private int numberOfUnits;
    private int daysBeforeExpire;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getDaysBeforeExpire() {
        return daysBeforeExpire;
    }

    public void setDaysBeforeExpire(int daysBeforeExpire) {
        this.daysBeforeExpire = daysBeforeExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName);
    }

    @Override
    public String toString() {
        return "{" +
                "\"foodId\":\"" + foodId + '\"' +
                ", \"foodName\":\"" + foodName + '\"' +
                ", \"numberOfUnits\":\"" + numberOfUnits + '\"' +
                ", \"daysBeforeExpire\":\"" + daysBeforeExpire + '\"' +
                '}';
    }
}
