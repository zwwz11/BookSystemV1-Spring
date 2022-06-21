package basicBookManagement.springBasic.bookService.domain.book.service;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;
import basicBookManagement.springBasic.bookService.domain.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> add(Book book) {
        Optional<Book> savedBook = bookRepository.save(book);
        return savedBook;
    }

    @Override
    public Optional<Book> findBook(Long bookId) {
        Optional<Book> findedBook = bookRepository.findById(bookId);
        return findedBook;
    }

    @Override
    public Optional<List<Book>> findAllBook() {
        Optional<List<Book>> books = bookRepository.findAll();
        return books;
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.removeBook(book);
    }

    @Override
    public void editBook(Long id, Book book) {
        bookRepository.updateBook(id, book);
    }
}
