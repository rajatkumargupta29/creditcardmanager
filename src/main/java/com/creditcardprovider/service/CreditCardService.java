package com.creditcardprovider.service;

import com.creditcardprovider.model.CreditCard;
import com.creditcardprovider.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Transactional
    public CreditCard addNewCard(CreditCard creditCard) {
        if (!creditCardRepository.existsByCardNumber(creditCard.getCardNumber())) {
            validateCreditCard(creditCard.getCardNumber());
            creditCard.setBalance(new BigDecimal(0));
            return creditCardRepository.save(creditCard);
        } else {
            throw new IllegalArgumentException("Credit card number already in use");
        }
    }

    private void validateCreditCard(String creditCardNumber) {

        int length = creditCardNumber.length();

        if (length > 19 || !creditCardNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid credit card number");
        }

        int sum = 0;
        boolean isToBeDoubled = false;

        for (int i = length - 1; i > -1; i--) {

            int num = creditCardNumber.charAt(i) - '0';

            if(isToBeDoubled) {
                num *= 2;
                sum += num/10;
                sum += num%10;
            } else {
                sum += num;
            }

            isToBeDoubled = !isToBeDoubled;
        }

        if(sum % 10 != 0) {
            throw new IllegalArgumentException("Invalid credit card number");
        }
    }

    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }
}
