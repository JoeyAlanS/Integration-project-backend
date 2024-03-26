package dao;

import java.util.List;

import model.LineupEntity;
import org.hibernate.Session;

public class LineupDAO {
    private final Session session;

    public LineupDAO(Session session) {
        this.session = session;
    }

    public List<LineupEntity> getAllLineups() {

        return session.createQuery("FROM LineupEntity").list();
    }
}
