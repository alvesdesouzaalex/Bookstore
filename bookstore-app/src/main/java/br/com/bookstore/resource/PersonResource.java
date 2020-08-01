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

import br.com.bookstore.data.vo.PersonVO;
import br.com.bookstore.service.PersonService;
import io.swagger.annotations.Api;

@Api(tags = {"Person of Resource"})
@RestController
@RequestMapping("/person")
public class PersonResource {

    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonVO add(@RequestBody PersonVO personVO) {
        return this.personService.add(personVO);
    }

    @PutMapping("/{id}")
    public PersonVO update(@PathVariable(name = "id") Long id, @RequestBody PersonVO personVO) {
        return this.personService.update(id, personVO);
    }

    @GetMapping("/all-person")
    public ResponseEntity<List<PersonVO>> listAll() {
        return ResponseEntity.ok(this.personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> find(@PathVariable(name = "id") Long id) {
        PersonVO personVO = this.personService.find(id);
        personVO.add(linkTo(PersonResource.class).slash(personVO.getId()).withSelfRel());
        return ResponseEntity.ok(personVO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.personService.delete(id);
    }
}
