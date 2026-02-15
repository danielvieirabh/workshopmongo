package com.danzin.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class URL {
    public static String decodeParam(String text) { //Static para nao instanciar um objeto URL
        try {
            return URLDecoder.decode(text, "UTF-8"); //decodificacao : colocar o texto , e forma de decodificacao original da WEB que e UTF-8
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static LocalDateTime convertDate(String textDate, LocalDateTime defaultValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(textDate, formatter);
        }
        catch (Exception error) {
            return defaultValue; //data default
        }


    }
}
