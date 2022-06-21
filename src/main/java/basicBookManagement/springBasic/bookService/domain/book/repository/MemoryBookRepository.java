package basicBookManagement.springBasic.bookService.domain.book.repository;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBookRepository implements BookRepository{

    private static final Map<Long, Book> store = new HashMap<>();
    private static Long seq = 0L;

    @Override
    public void updateBook(Long id, Book book) {
        Book findBook = store.get(id);
        findBook.setName(book.getName());
        findBook.setPrice(book.getPrice());
    }

    @Override
    public void clearStore() {
        store.clear();
    }

    @Override
    public Optional<Book> save(Book book) {
        book.setId(++seq);
        store.put(seq, book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book findBook = store.get(id);
        return Optional.of(findBook);
    }

    @Override
    public Optional<List<Book>> findAll() {
        List<Book> books = store.values().stream().toList();
        return Optional.of(books);
    }

    @Override
    public void removeBook(Book book) {
        Long findId = book.getId();
        store.remove(findId);
    }
}
