package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;

    public static Book createBook(BookForm bookForm){
        Book book = new Book();
        book.isbn=bookForm.getIsbn();
        book.author=bookForm.getAuthor();
        book.setStockQuantity(book.getStockQuantity());
        book.setPrice(bookForm.getPrice());
        book.setName(bookForm.getName());
        return book;
    }
}
