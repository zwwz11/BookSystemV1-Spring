package basicBookManagement.springBasic.bookService.domain.book.service;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> add(Book book);
    Optional<Book> findBook(Long bookId);
    Optional<List<Book>> findAllBook();
    void deleteBook(Book book);
    void editBook(Long id, Book book);
}
