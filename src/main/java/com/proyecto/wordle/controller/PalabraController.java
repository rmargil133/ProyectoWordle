package com.proyecto.wordle.controller;


import com.proyecto.wordle.errors.PalabraNoEncontrada;
import com.proyecto.wordle.model.Palabra;
import lombok.AllArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
public class PalabraController {

    /**
     * Obtenemos todas las palabras
     *
     * @return lista de palabras
     */
    @GetMapping("/palabras")
    public ResponseEntity<?> getAllPalabras() throws Exception {
        Palabra palabra = new Palabra();
        List<String> allPalabras = palabra.leer("diccionario.txt");

        if (allPalabras.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allPalabras);

    }


    /**
     * Obtenemos una palabra aleatoria.
     *
     * @return una palabra aleatoria de la lista de palabras.
     */
    @GetMapping("/palabra/aleatoria")
    public ResponseEntity<?> getPalabraAleatoria() throws Exception {
        Palabra palabra = new Palabra();
        List<String> allPalabras = palabra.leer("diccionario.txt");

        if (allPalabras.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();

        int randomIndex = random.nextInt(allPalabras.size());
        return ResponseEntity.ok(allPalabras.get(randomIndex));
    }


    /**
     * Obtenemos las palabras que contienen una cedena.
     *
     * @param cadena
     * @return lista de palabras que contienen esa cadena.
     */
    @GetMapping("/palabras/contienen/{cadena}")
    public ResponseEntity<?> getPalabrasContienen(@PathVariable String cadena)  throws Exception{
        Palabra palabra = new Palabra();
        List<String> allPalabras = palabra.leer("diccionario.txt");

        if (allPalabras.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<String> palabarasContienenCadena = new ArrayList<>();
        for(String p : allPalabras){
            if(p.contains(cadena)){
                palabarasContienenCadena.add(p);
            }
        }

        return ResponseEntity.ok(palabarasContienenCadena);
    }


    /**
     * Obtenemos palabras que empiecen por una cadena.
     *
     * @param cadena
     * @return Lista de palabras que empiecen por una cadena.
     */
    @GetMapping("/palabras/empiezan/{cadena}")
    public ResponseEntity<?> getAllPalabrasEmpiezanCon(@PathVariable String cadena) throws Exception{
        Palabra palabra = new Palabra();
        List<String> allPalabras = palabra.leer("diccionario.txt");

        if (allPalabras.isEmpty()){
            throw new PalabraNoEncontrada(cadena);
        }

        List<String> palabrasEmpiezanPor = new ArrayList<>();
        for (String p : allPalabras){
            if(p.startsWith(cadena)){
                palabrasEmpiezanPor.add(p);
            }
        }

        return ResponseEntity.ok(palabrasEmpiezanPor);
    }


    /**
     * Obtenemos las palabras que terminan por una cadena.
     *
     * @param cadena
     * @return Lista de palabras que terminan por una cadena.
     */
    @GetMapping("/palabras/terminan/{cadena}")
    public ResponseEntity<?> getAllPalabrasTerminanCon(@PathVariable String cadena) throws Exception {
        Palabra palabra = new Palabra();
        List<String> allPalabras = palabra.leer("diccionario.txt");

        if(allPalabras.isEmpty()){
            throw new PalabraNoEncontrada(cadena);
        }

        List<String> palabrasTerminanPor = new ArrayList<>();
        for (String p : allPalabras){
            if (p.endsWith(cadena)){
                palabrasTerminanPor.add(p);
            }
        }
        return ResponseEntity.ok(palabrasTerminanPor);
    }
}
