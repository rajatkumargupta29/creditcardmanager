package com.creditcardprovider.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_cards")
@Builder
@Data
public class CreditCard {
    @Id
    private String cardNumber;
    private String name;
    BigDecimal cardLimit;
    BigDecimal balance;
}
