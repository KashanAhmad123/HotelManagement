package com.example;

import com.example.appConfig.AppConfig;
import com.example.consoleUI.ConsoleUI;
import com.example.services.HotelService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
       try{
           AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


           // Retrieve beans from the context
           HotelService hotelService = context.getBean(HotelService.class);
           ConsoleUI consoleUI = context.getBean(ConsoleUI.class);

           // Start the application
           consoleUI.start();

           // Close the application context
           context.close();
       }catch (Exception e){

           System.out.println(" Message " +e.getMessage());
       }
    }
}