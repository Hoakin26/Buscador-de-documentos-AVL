package com.busquedas.avl.arbolAvl;
import com.busquedas.avl.modelo.DocumentoInfo;
import java.util.HashMap;
import java.util.Map;

public class NodoAvl {

    public String key;
    public Map<DocumentoInfo, Integer> documentosConFrecuencias;
    int altura;
    NodoAvl izquierda, derecha;

    public NodoAvl(String key) {
        this.key = key;
        this.izquierda = this.derecha = null;
        this.altura = 1;
        this.documentosConFrecuencias = new HashMap<>();
    }

    public void aggFrecuencia(DocumentoInfo doc) {
        documentosConFrecuencias.put(doc, documentosConFrecuencias.getOrDefault(doc, 0) + 1);
    }
}
