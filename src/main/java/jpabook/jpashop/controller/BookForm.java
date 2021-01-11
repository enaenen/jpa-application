package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public static BookForm createBookForm(Book item){
        BookForm bookForm = new BookForm();
        bookForm.id=item.getId();
        bookForm.isbn=item.getIsbn();
        bookForm.author=item.getAuthor();
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setPrice(item.getPrice());
        bookForm.setName(item.getName());
        return bookForm;
    }
}
