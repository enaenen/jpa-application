package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import jpabook.jpashop.dto.UpdateItemDto;
import lombok.Getter;
import lombok.Setter;

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
        book.setId(bookForm.getId());
        book.isbn=bookForm.getIsbn();
        book.author=bookForm.getAuthor();
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setPrice(bookForm.getPrice());
        book.setName(bookForm.getName());
        return book;
    }
    public void updateItem(UpdateItemDto bookUpdate){
        this.isbn=bookUpdate.getIsbn();
        this.author=bookUpdate.getAuthor();
        this.updateItem(bookUpdate.getName(),bookUpdate.getPrice(),bookUpdate.getStockQuantity());
    }
}
