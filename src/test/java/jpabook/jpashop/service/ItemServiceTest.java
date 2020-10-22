package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void 상품_저장() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("chae");
        book.setIsbn("1234");
        book.addStock(2);
        //when
        Long itemId = itemService.saveItem(book);
        //then
        assertEquals(book,itemService.findOne(itemId));
    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품_차감_예외() throws Exception {
        //given
        Book book = new Book();
        book.setIsbn("1234");
        book.setAuthor("chae");
        book.addStock(2);
        //when
        book.removeStock(3);
        //then
        fail("예외가 발생해야 한다.");
    }
}