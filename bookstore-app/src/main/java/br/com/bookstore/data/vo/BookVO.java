package br.com.bookstore.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private Long id;
    private String firstName;
    private LocalDate launchDate;
    private BigDecimal price;
    private String title;
}
