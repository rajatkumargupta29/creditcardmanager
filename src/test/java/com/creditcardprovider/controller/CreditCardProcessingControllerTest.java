package com.creditcardprovider.controller;

import com.creditcardprovider.model.CreditCard;
import com.creditcardprovider.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreditCardProcessingControllerTest {

    @Mock
    CreditCardService creditCardService;

    @InjectMocks
    CreditCardProcessingController creditCardProcessingController;

    @Test
    public void addNewCardTest() {
        CreditCard testCard = CreditCard.builder().name("John Doe")
                .cardLimit(new BigDecimal(1000))
                .cardNumber("5550510279602602")
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(creditCardService.addNewCard(Mockito.any(CreditCard.class))).thenReturn(testCard);

        assertEquals(creditCardProcessingController.addCreditCard(testCard), testCard);
    }

    @Test
    public void getAllCreditCardTest() {
        CreditCard testCard = CreditCard.builder().name("John Doe")
                .cardLimit(new BigDecimal(1000))
                .cardNumber("5550510279602602")
                .balance(new BigDecimal(0))
                .build();

        Mockito.when(creditCardService.getAllCards()).thenReturn(List.of(testCard));

        assertEquals(creditCardProcessingController.getAllCreditCardDetails(), List.of(testCard));
    }
}
