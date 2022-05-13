package com.whattoshake.exception.model;

public class UserNotFoundException extends ModelException {
    public UserNotFoundException(String message) {
        super(message);
    }
}