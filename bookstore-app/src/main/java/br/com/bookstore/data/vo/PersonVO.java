package br.com.bookstore.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "address", "first_name", "lastName", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
