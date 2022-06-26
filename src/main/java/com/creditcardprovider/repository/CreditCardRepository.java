package com.creditcardprovider.repository;

import com.creditcardprovider.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, BigDecimal> {

    boolean existsByCardNumber(String cardNumber);
}
