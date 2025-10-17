package com.bms.central_api_v1.exception;

public class AlredyBookedSeatException extends RuntimeException{
    public AlredyBookedSeatException(String message){
        super(message);
    }
}
