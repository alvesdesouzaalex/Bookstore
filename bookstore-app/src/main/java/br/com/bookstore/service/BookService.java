package br.com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.converter.DozerConverter;
import br.com.bookstore.data.model.Book;
import br.com.bookstore.data.vo.BookVO;
import br.com.bookstore.exception.ResourceNotFoundException;
import br.com.bookstore.repository.BookRepository;


@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookVO add(BookVO bookVO) {
        var entity = DozerConverter.parseObject(bookVO, Book.class);
        return DozerConverter.parseObject(this.bookRepository.save(entity), BookVO.class);
    }

    public BookVO update(Long id, BookVO bookVO) {
        this.findBook(id);
        var entity = DozerConverter.parseObject(bookVO, Book.class);
        return DozerConverter.parseObject(this.bookRepository.saveAndFlush(entity), BookVO.class);
    }

    public List<BookVO> findAll() {

//        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo();

        List<Book> books = this.bookRepository.findAll();
        if (books.isEmpty()) return new ArrayList<>();

        return DozerConverter.parseListObjects(books, BookVO.class);
    }

    public void delete(Long id) {
        this.bookRepository.delete(this.findBook(id));
    }

    public BookVO find(Long id) {
        var book = this.findBook(id);
        return DozerConverter.parseObject(book, BookVO.class);
    }

    private Book findBook(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found this resource"));
    }
}
