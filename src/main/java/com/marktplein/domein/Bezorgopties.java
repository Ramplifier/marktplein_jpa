package com.marktplein.domein;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class Bezorgopties {
    private boolean afhalen;
    private boolean ophalen;
    private boolean versturen;
    private boolean rembours;
}
