package br.com.bookstore.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookstore.data.vo.BookVO;
import br.com.bookstore.service.BookService;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.Operation;

@ApiModel(value = "BookEntrypoint")
@RestController
@RequestMapping("/book")
public class BookResource {

    private final BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(description = "Teste Opa ADD", tags = {"method for add new book resource"})
    @PostMapping
    public BookVO add(@RequestBody BookVO bookVO) {
        return this.bookService.add(bookVO);
    }

    @PutMapping("/{id}")
    public BookVO update(@PathVariable(name = "id") Long id, @RequestBody BookVO bookVO) {
        return this.bookService.update(id, bookVO);
    }

    @GetMapping("/all-book")
    public ResponseEntity<List<BookVO>> listAll() {
        return ResponseEntity.ok(this.bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookVO> find(@PathVariable(name = "id") Long id) {
        BookVO bookVO = this.bookService.find(id);
        bookVO.add(linkTo(BookResource.class).slash(bookVO.getId()).withSelfRel());
        return ResponseEntity.ok(bookVO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.bookService.delete(id);
    }
}
