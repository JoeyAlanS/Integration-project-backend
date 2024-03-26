package dao;

import java.util.List;

import org.hibernate.Session;
import model.CategoryEntity;
import model.LineupEntity;

public class CategoryDAO {
    private final Session session;

    public CategoryDAO(Session session) {
        this.session = session;
    }

    public List<CategoryEntity> getCategoriesForLine(LineupEntity line) {
        return session.createQuery("FROM CategoryEntity WHERE line = :line").setParameter("line", line).list();
    }
}
