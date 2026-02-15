package com.danzin.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
    public static String decodeParam(String text) { //Static para nao instanciar um objeto URL
        try {
            return URLDecoder.decode(text, "UTF-8"); //decodificacao : colocar o texto , e forma de decodificacao original da WEB que e UTF-8
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
