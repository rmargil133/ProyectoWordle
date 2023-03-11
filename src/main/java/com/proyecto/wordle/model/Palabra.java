package com.proyecto.wordle.model;


import org.springframework.util.ResourceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Palabra {
    public List<String> leer (String archivo) throws Exception{
        List<String> palabras = new ArrayList<String>();

        File file = ResourceUtils.getFile("classpath:" + archivo);
        BufferedReader  lector = new BufferedReader(new FileReader(file));
        String linea;

        while ((linea = lector.readLine()) != null){

            String palabra = linea.trim();
            palabras.add(palabra);

        }

        lector.close();
        return palabras;
    }
}
