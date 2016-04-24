package com.pik;

/**
 * Obiekt, który jest przesyłany z powrotem do przeglądarki internetowej.
 */
public class ResponseObject {

    private String message;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
