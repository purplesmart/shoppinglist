package com.shopping.ExceptionsHandle.Exceptions;

public class CatalogSourceNotFoundException extends Exception {
    public CatalogSourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
