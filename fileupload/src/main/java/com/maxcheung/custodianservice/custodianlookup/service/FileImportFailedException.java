package com.maxcheung.custodianservice.custodianlookup.service;


@SuppressWarnings("serial")
public class FileImportFailedException extends RuntimeException {

    public FileImportFailedException() {
        super();

    }

    public FileImportFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FileImportFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileImportFailedException(String message) {
        super(message);
    }

    public FileImportFailedException(Throwable cause) {
        super(cause);
    }

}
