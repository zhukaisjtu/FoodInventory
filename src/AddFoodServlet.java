import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFoodServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        Food food = new Food();
        food.setFoodName(req.getParameter("foodName"));
        food.setNumberOfUnits(Integer.parseInt(req.getParameter("numberOfUnits")));
        food.setDaysBeforeExpire(Integer.parseInt(req.getParameter("daysBeforeExpire")));
        FoodManager foodManager = new FoodManager();
        boolean isAdded = foodManager.addFood(food);
        if (isAdded) {
            try {
                resp.getWriter().print(food);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
