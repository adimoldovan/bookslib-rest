package am.tau.bookslib.service;

import am.tau.bookslib.db.HibernateUtil;
import am.tau.bookslib.model.Genre;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class GenreService {
    public Iterable<Genre> getAll() {
        List<Genre> genres;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Genre> cq = session.getCriteriaBuilder().createQuery(Genre.class);
            cq.from(Genre.class);
            genres = session.createQuery(cq).getResultList();
        }
        return genres;
    }

    public Genre getById(int id) {
        Genre genre;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            genre = session.get(Genre.class, id);
        }
        return genre;
    }

    public void add(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        }
    }

    public void update(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(genre);
            session.getTransaction().commit();
        }
    }

    public void delete(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(Genre.class, id));
            session.getTransaction().commit();
        }
    }
}
