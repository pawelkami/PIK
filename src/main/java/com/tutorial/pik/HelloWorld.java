package com.tutorial.pik;

/**
 * Created by Jan on 19.03.2016.
 */

public class HelloWorld {
    private String message;

    public void setMessage(String message){
        this.message  = message;
    }

    public void getMessage(){
        System.out.println("Your Message : " + message);
    }
}