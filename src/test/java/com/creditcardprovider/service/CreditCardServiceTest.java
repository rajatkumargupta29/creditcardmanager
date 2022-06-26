package com.creditcardprovider.service;

import com.creditcardprovider.model.CreditCard;
import com.creditcardprovider.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {

    @InjectMocks
    CreditCardService creditCardService;

    @Mock
    CreditCardRepository creditCardRepository;

    @Test
    public void addCorrectCreditCard() {
        CreditCard creditCard = CreditCard.builder()
                .cardNumber("5550510279602602")
                .name("Rajat")
                .cardLimit(new BigDecimal(1000))
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        assertEquals(creditCard, creditCardService.addNewCard(creditCard));
    }

    @Test
    public void addInvalidCreditCard() {
        CreditCard creditCard = CreditCard.builder()
                .cardNumber("5550510279602102")
                .name("Rajat")
                .cardLimit(new BigDecimal(1000))
                .balance(new BigDecimal(0))
                .build();

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> creditCardService.addNewCard(creditCard));

        assertEquals(e.getMessage(), "Invalid credit card number");
    }

    @Test
    public void addDuplicateCreditCard() {
        CreditCard creditCard = CreditCard.builder()
                .cardNumber("5550510279602602")
                .name("Rajat")
                .cardLimit(new BigDecimal(1000))
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(creditCardRepository.existsByCardNumber(Mockito.anyString())).thenReturn(true);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> creditCardService.addNewCard(creditCard));

        assertEquals(e.getMessage(), "Credit card number already in use");
    }

    @Test
    public void getAllCreditCardTest() {
        CreditCard testCard = CreditCard.builder().name("John Doe")
                .cardLimit(new BigDecimal(1000))
                .cardNumber("5550510279602602")
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(creditCardRepository.findAll()).thenReturn(List.of(testCard));

        assertEquals(creditCardService.getAllCards(), List.of(testCard));
    }
}
