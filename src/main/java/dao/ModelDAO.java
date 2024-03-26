package dao;

import java.util.List;

import model.CategoryEntity;
import model.ModelEntity;
import org.hibernate.Session;

public class ModelDAO {
    private final Session session;

    public ModelDAO(Session session) {

        this.session = session;
    }

    public List<ModelEntity> getModelsForCategory(CategoryEntity category) {
        return session.createQuery("FROM ModelEntity WHERE category = :category").setParameter("category", category).list();
    }
}
