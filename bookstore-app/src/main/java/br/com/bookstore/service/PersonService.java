package br.com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bookstore.converter.DozerConverter;
import br.com.bookstore.data.model.Person;
import br.com.bookstore.data.vo.PersonVO;
import br.com.bookstore.exception.ResourceNotFoundException;
import br.com.bookstore.repository.PersonRepository;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO add(PersonVO personVO) {
        var entity = DozerConverter.parseObject(personVO, Person.class);
        return DozerConverter.parseObject(this.personRepository.save(entity), PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO personVO) {
        this.findPerson(id);
        var entity = DozerConverter.parseObject(personVO, Person.class);
        return DozerConverter.parseObject(this.personRepository.saveAndFlush(entity), PersonVO.class);
    }

    public List<PersonVO> findAll() {

//        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo();

        List<Person> persons = this.personRepository.findAll();
        if (persons.isEmpty()) return new ArrayList<>();

        return DozerConverter.parseListObjects(persons, PersonVO.class);
    }

    public void delete(Long id) {
        this.personRepository.delete(this.findPerson(id));
    }

    public PersonVO find(Long id) {
        var person = this.findPerson(id);
        return DozerConverter.parseObject(person, PersonVO.class);
    }

    private Person findPerson(Long id) {
        return this.personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not find this resource"));
    }
}
