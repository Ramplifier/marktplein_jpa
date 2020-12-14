package com.marktplein.domein;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data

public class Product extends Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private LocalDate datumPlaatsing;
    private ProductStatus status;
    @Embedded
    private Bezorgopties bezorgopties;
}
