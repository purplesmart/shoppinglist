package com.shopping.ExceptionsHandle.Exceptions;

public class CatalogSourceLoadingFailureException  extends Exception {
    public CatalogSourceLoadingFailureException(String errorMessage) {
        super(errorMessage);
    }
}
