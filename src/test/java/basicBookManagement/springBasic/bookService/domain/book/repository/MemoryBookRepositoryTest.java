package basicBookManagement.springBasic.bookService.domain.book.repository;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryBookRepositoryTest {
    BookRepository bookRepository =new MemoryBookRepository();

    @AfterEach
    void afterEach(){
        bookRepository.clearStore();
    }

    @Test
    @DisplayName("Book 저장 성공")
    void save() {
        Book book = new Book();
        book.setName("bookA");
        book.setPrice(1000);
        Optional<Book> savedBook = bookRepository.save(book);
        if(!savedBook.isEmpty()) {
            assertThat(book).isEqualTo(savedBook.get());
        }
    }

    @Test
    @DisplayName("Book 저장 실패")
    void notSaved() {
        Book book = new Book();
        book.setName("bookA");
        book.setPrice(1000);
        Optional<Book> savedBook = Optional.empty();
        if(savedBook.isEmpty()) {
            assertThrows(NoSuchElementException.class, () -> bookRepository.findById(savedBook.get().getId()));
        }
    }

    @Test
    @DisplayName("Book 찾기 성공")
    void findById() {
        Book book = new Book();
        book.setName("bookA");
        book.setPrice(1000);
        Optional<Book> savedBook = bookRepository.save(book);

        assertThat(book).isEqualTo(savedBook.get());
    }

    @Test
    @DisplayName("모든 Book 찾기 성공")
    void findAll() {
        Book bookA = new Book();
        bookA.setName("bookA");
        bookA.setPrice(1000);
        bookRepository.save(bookA);

        Book bookB = new Book();
        bookB.setName("bookB");
        bookB.setPrice(1000);
        bookRepository.save(bookB);
        Optional<List<Book>> books = bookRepository.findAll();

        assertThat(2).isEqualTo(books.get().stream().count());
    }

    @Test
    @DisplayName("Book 삭제 성공")
    void removeBook() {
        Book bookA = new Book();
        bookA.setName("bookA");
        bookA.setPrice(1000);
        Optional<Book> savedBook = bookRepository.save(bookA);

        bookRepository.removeBook(bookA);
        Optional<List<Book>> books = bookRepository.findAll();
        assertThat(0).isEqualTo(books.get().stream().count());
    }
}