package com.busquedas.avl.indexacion;
import java.util.HashSet;
public class OmitirPalabras {
    private final HashSet<String> palabrasOmitidas;

    public OmitirPalabras() {
        palabrasOmitidas = new HashSet<>();
        initialize();
    }
    private void initialize() {
        String[] palabrasComunes = {
                "el", "la", "los", "las", "un", "una", "unos", "unas", "de", 
                "del", "en", "y", "o", "que", "a", "con", "por", "para", "se", 
                "es", "al", "como", "más", "pero", "ya", "fue", "ha", "le", 
                "lo", "su", "si", "no", "me", "te", "mi", "tu", "él", "ella",
                "ellos", "ellas", "nosotros", "ustedes", "también", "este", 
                "esa", "eso", "sus", "algunas", "ninguno", "cual", "cuales", 
                "donde", "cuando", "quien", "quienes", "cuyo", "cuya", "cuyos", 
                "cuyas", "esto", "esta", "estos", "estas", "aquel", "aquella", 
                "aquellos", "aquellas", "otro", "otra", "otros", "otras", "tan", 
                "tanto", "poco", "mucho", "muchos", "muchas", "cada", "algún", 
                "ningún", "toda", "todos", "todas", "dicho", "misma", "mismo", 
                "mismos", "mismas", "ese", "aquello", "he", "has", "han", "haya", 
                "hay", "hoy", "ayer", "entonces", "ahora", "aún", "aunque", 
                "mientras", "luego", "antes", "después", "durante", "sobre", 
                "entre", "hacia", "mediante", "sin", "tras", "versus", "excepto", 
                "yo", "vosotros", "porque", "tal", "qué", "sí", "hasta", "ahí", "así",
                "son", "sino", "ni", "the", "etc", "sea", "ser"
        };
        for (String palabra : palabrasComunes) {
            palabrasOmitidas.add(palabra);
        }
    }
    public boolean esPalabraOmitida(String palabra) {
        return palabrasOmitidas.contains(palabra);
    }
}
