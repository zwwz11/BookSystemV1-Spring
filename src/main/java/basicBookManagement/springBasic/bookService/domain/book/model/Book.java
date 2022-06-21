package basicBookManagement.springBasic.bookService.domain.book.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Integer price;

    public Book(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
