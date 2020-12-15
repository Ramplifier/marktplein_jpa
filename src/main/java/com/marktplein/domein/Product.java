package com.marktplein.domein;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data

public class Product extends Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private LocalDate datumPlaatsing;
    private ProductStatus status = ProductStatus.TEKOOP;
    @Embedded
    private Bezorgopties bezorgopties;
}
