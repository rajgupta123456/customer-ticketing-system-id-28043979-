package com.ticketingsystem.exception;

public class TicketingException extends Exception {
    public TicketingException(String message) {
        super(message);
    }

    public TicketingException(String message, Throwable cause) {
        super(message, cause);
    }
}
