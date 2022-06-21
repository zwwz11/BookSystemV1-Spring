package basicBookManagement.springBasic.bookService.web.controller;

import basicBookManagement.springBasic.bookService.domain.book.model.Book;
import basicBookManagement.springBasic.bookService.domain.book.repository.BookRepository;
import basicBookManagement.springBasic.bookService.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String books(Model model) {
        Optional<List<Book>> books = bookService.findAllBook();
        if(books.isEmpty()){
            model.addAttribute("books", new ArrayList<Book>());
        }
        else {
            model.addAttribute("books", books.get().stream().toList());
        }
        return "book/books";
    }

    @GetMapping("/addBook")
    public String book() {
        return "book/addBook";
    }

    @PostMapping("/addBook")
    public String addBook(Book book, RedirectAttributes redirectAttributes) {
        Optional<Book> savedBook = bookService.add(book);
        redirectAttributes.addAttribute("bookId", savedBook.get().getId());
        return "redirect:/book/{bookId}";
    }

    @GetMapping("{bookId}")
    public String book(@PathVariable Long bookId, Model model) {
        Optional<Book> book = bookService.findBook(bookId);
        model.addAttribute("book", book.get());
        return "book/book";
    }

    @GetMapping("{bookId}/edit")
    public String editBookForm(@PathVariable Long bookId, Model model) {
        Optional<Book> findBook = bookService.findBook(bookId);
        model.addAttribute("book", findBook.get());
        return "book/editBook";
    }

    @PostMapping("{bookId}/edit")
    public String editBook(@PathVariable Long bookId, @ModelAttribute Book book) {
        bookService.editBook(bookId, book);
        return "redirect:/book/{bookId}";
    }

    @GetMapping("{bookId}/delete")
    public String delete(@PathVariable Long bookId) {
        Optional<Book> findBook = bookService.findBook(bookId);
        bookService.deleteBook(findBook.get());
        return "redirect:/book/books";
    }
}
