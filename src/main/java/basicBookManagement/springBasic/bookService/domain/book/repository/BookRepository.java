package basicBookManagement.springBasic.bookService.domain.book.repository;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> save(Book book);
    Optional<Book> findById(Long id);
    Optional<List<Book>> findAll();
    void removeBook(Book book);
    void clearStore();
    void updateBook(Long id, Book book);
}
