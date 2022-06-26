package com.creditcardprovider.controller;

import com.creditcardprovider.model.ApiError;
import com.creditcardprovider.model.CreditCard;
import com.creditcardprovider.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("credit-card")
public class CreditCardProcessingController {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("add")
    public CreditCard addCreditCard(@RequestBody CreditCard creditCard) throws IllegalArgumentException {
        return creditCardService.addNewCard(creditCard);
    }

    @GetMapping("getAll")
    public List<CreditCard> getAllCreditCardDetails() {
        return creditCardService.getAllCards();
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<ApiError> handleException(IllegalArgumentException iae) {
        return new ResponseEntity<>(new ApiError("INVALID_OR_USED_CARD", iae.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
