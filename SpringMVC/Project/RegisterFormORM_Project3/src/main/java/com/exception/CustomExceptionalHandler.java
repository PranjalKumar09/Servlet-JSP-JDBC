    package com.exception;

    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;

    @ControllerAdvice
    public class CustomExceptionalHandler {

        @ExceptionHandler(Exception.class)
        public String exception(){
            System.out.println("exception");
            return "error";

        }

        @ExceptionHandler(NumberFormatException.class)
        public String numberException(){
            System.out.println("numberException");
            return "error";
        }

    }
