import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FoodManager {

    public boolean addFood(Food food) {
        try {
            SessionFactory sessionFactory = DBManager.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            food.setFoodId(generateUniqueFoodId());
            Date today = Calendar.getInstance().getTime();
            food.setBuyDate(today);
            session.save(food);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFood(Food food) {
        SessionFactory sessionFactory = DBManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(food);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean deleteFood(Food food) {
        SessionFactory sessionFactory = DBManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(food);
        transaction.commit();
        session.close();
        return true;
    }

    public List<Food> searchFoodByName(String foodName) {
        SessionFactory sessionFactory = DBManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Food> list = session.createQuery("from Food where foodName like'%"+foodName+"%'").getResultList();
        return list;

    }

    public boolean isFoodNameInDB(String foodName) {
        SessionFactory sessionFactory = DBManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<String> list = session.createQuery("select foodName from Food").getResultList();
        return list.contains(foodName) ? true:false;

    }

    protected int generateUniqueFoodId() {
        SessionFactory sessionFactory = DBManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Integer> list = session.createQuery("select foodId from Food order by foodId desc").getResultList();
        if(list == null || list.isEmpty()) {
            return 1;
        } else {
            int newId = list.get(0) + 1;
            return newId;
        }
    }

}

