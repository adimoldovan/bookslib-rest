package am.tau.bookslib.service;

import am.tau.bookslib.db.HibernateUtil;
import am.tau.bookslib.model.Author;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class AuthorService {
    public Iterable<Author> getAll() {
        List<Author> authors;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Author> cq = session.getCriteriaBuilder().createQuery(Author.class);
            cq.from(Author.class);
            authors = session.createQuery(cq).getResultList();
        }
        return authors;
    }

    public Author getById(int id) {
        Author author;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            author = session.get(Author.class, id);
        }
        return author;
    }

    public void add(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        }
    }

    public void update(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
        }
    }

    public void delete(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(Author.class, id));
            session.getTransaction().commit();
        }
    }
}
