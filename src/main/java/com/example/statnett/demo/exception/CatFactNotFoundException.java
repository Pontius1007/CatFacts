package com.example.statnett.demo.exception;

public class CatFactNotFoundException extends RuntimeException {

    public CatFactNotFoundException(Long id) {
        super("=^-_-^= Could not find cat fact " + id);
    }
}
