import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    public List<Book> findBorrowedByReader(Integer readerId) {
        return em.createQuery(
                        "SELECT b FROM Book b JOIN b.borrowedBooks bb " +
                                "WHERE bb.reader.readerId = :readerId AND bb.status = 'BORROWED'", Book.class)
                .setParameter("readerId", readerId)
                .getResultList();
    }
}
