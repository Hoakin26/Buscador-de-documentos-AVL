package com.busquedas.avl.arbolAvl;
import com.busquedas.avl.modelo.DocumentoInfo;
import java.util.HashMap;
import java.util.Map;

public class ArbolAvl {
    private NodoAvl raiz;
    private int contadorComparaciones = 0;

    private int getAltura(NodoAvl nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int getBalance(NodoAvl nodo) {
        return (nodo == null) ? 0 : getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }

    private NodoAvl rotacionDerecha(NodoAvl y) {
        NodoAvl x = y.izquierda;
        NodoAvl T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;

        return x;
    }

    private NodoAvl rotacionIzquierda(NodoAvl x) {
        NodoAvl y = x.derecha;
        NodoAvl T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;

        return y;
    }

    // Insertar archivos--Modificar
    public void insertar(String key, DocumentoInfo docInfo) {
        raiz = insertarDoc(raiz, key.toLowerCase(), docInfo);
    }

    public NodoAvl insertarDoc(NodoAvl nodo, String key, DocumentoInfo docInfo) {
        System.out.println("Recorriendo Arbol | PALABRA: " + key);
        if (nodo == null) {
            NodoAvl nuevo = new NodoAvl(key);
            nuevo.aggFrecuencia(docInfo);
            System.out.println("Creando Nodo de palabra: " + key);
            return nuevo;
        }

        contadorComparaciones++;
        //COMPARA SI LA PALABRA ES MAYOR, MENOR O IGUAL A LA PALABRA DEL NODO
        int cmp = key.compareTo(nodo.key);

        if (cmp < 0) {
            nodo.izquierda = insertarDoc(nodo.izquierda, key, docInfo);
        } else if (cmp > 0) {
            nodo.derecha = insertarDoc(nodo.derecha, key, docInfo);
        } else {
            //SI LAS PALABRAS COMPARADAS SON IGUALES SOLO SE AGREGARA EL DOCUMENTO
            nodo.aggFrecuencia(docInfo);
            return nodo;
        }
        
        nodo.altura = Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha)) + 1;
        return balance(nodo);
    }

    public Map<DocumentoInfo, Integer> buscar(String key) {
        contadorComparaciones = 0;
        return buscarDoc(raiz, key.toLowerCase());
    }

    private Map<DocumentoInfo, Integer> buscarDoc(NodoAvl nodo, String key) {
         System.out.println("Buscando palabra: '" + key + "'");
        if (nodo == null){
            System.out.println("No nodo");
            return new HashMap<>();
        }
            

        contadorComparaciones++;
        int cmp = key.compareTo(nodo.key);

        if (cmp < 0) {
            return buscarDoc(nodo.izquierda, key);
        } else if (cmp > 0) {
            return buscarDoc(nodo.derecha, key);
        } else {
            return nodo.documentosConFrecuencias;
        }
    }

    public int getContadorComparaciones() {
        return contadorComparaciones;
    }

    private NodoAvl balance(NodoAvl nodo) {
        
        int balance = getBalance(nodo);

        //SI EL BALANCE ES MAYOR A 1 HACEMOS ROTACIÓN DERECHA
        if (balance > 1) {
            //SI EL NODO IZQUIERDO ES -1 HACEMOS ROTACIÓN DOBLE
            if (getBalance(nodo.izquierda) < 0)
                nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }

        //SI EL BALANCE ES MENOR A -1 HACEMOS ROTACIÓN DERECHA
        if (balance < -1) {
            //SI EL NODO DERECHO ES 1 HACEMOS ROTACIÓN DOBLE
            if (getBalance(nodo.derecha) > 0)
                nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void reiniciar() {
        this.raiz = null;
        this.contadorComparaciones = 0;
        System.out.println("--------------Arbol AVL reiniciado---------------");
    }
}
