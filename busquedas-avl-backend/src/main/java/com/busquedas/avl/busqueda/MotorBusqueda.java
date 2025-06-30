package com.busquedas.avl.busqueda;

import com.busquedas.avl.arbolAvl.ArbolAvl;
import com.busquedas.avl.modelo.DocumentoInfo;
import com.busquedas.avl.modelo.ListadoDocumentos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotorBusqueda {

    private final ArbolAvl arbolAvl;
    private final Estadistica estadistica;

    public MotorBusqueda(ArbolAvl arbolAvl, Estadistica estadistica) {
        this.arbolAvl = arbolAvl;
        this.estadistica = estadistica;
    }

    public Map<DocumentoInfo, Integer> buscar(String palabra) {
        System.out.println("Buscando............");

        Map<DocumentoInfo, Integer> resultados = arbolAvl.buscar(palabra);

        if (resultados.isEmpty() || resultados == null) {
            System.out.println("Sin resultados de busqueda");
        }

        return resultados;
    }

    public List<DocumentoInfo> buscarPalabras(String palabras) {
        List<ListadoDocumentos> lista = new ArrayList<>();
        Map<ListadoDocumentos, Integer> documentosRepetidos = new HashMap<>();
        String[] palabrasArray = palabras.split("[^\\p{L}\\p{N}]+");

        long tiempoInicio = System.currentTimeMillis();
        
        //HACIENDO BUSQUEDA DE DOCUMENTOS POR CADA PALABRA
        for (String p : palabrasArray) {
            Map<DocumentoInfo, Integer> resultado = buscar(p);
            for (DocumentoInfo doc : resultado.keySet()) {
                lista.add(new ListadoDocumentos(doc, resultado.get(doc)));
            }
        }
        long tiempoFin = System.currentTimeMillis();

        long tiempoTardado = tiempoFin - tiempoInicio;
        int comparaciones = arbolAvl.getContadorComparaciones();
        estadistica.aggBusqueda(tiempoTardado, comparaciones);
        
        //SI EL DOCUMENTO SE REPITE SOLO SE SUMA LA FRECUENCIA
        for(ListadoDocumentos l : lista){
            documentosRepetidos.put(l, documentosRepetidos.getOrDefault(l, 0)+1);
        }
        
        //SOLO SE OBTIENE LOS DOCUMENTOS CON LAS FRECUENCUIAS = N
        List<ListadoDocumentos> documentosConFrecuencias = new ArrayList<>();
        int totalPalabras = palabrasArray.length;
        for (Map.Entry<ListadoDocumentos, Integer> df : documentosRepetidos.entrySet()){
            if(df.getValue() == totalPalabras){
                documentosConFrecuencias.add(df.getKey());
            }
        }
        
        List<DocumentoInfo> resultados = new ArrayList<>();
        
        //SE ORDENA LA LISTA POR FRECUENCIAS REPETIDAS POR PALABRA
        documentosConFrecuencias.sort((a, b) -> Integer.compare(b.frecuenciaPalabra, a.frecuenciaPalabra));
        
        //SE GUARDAN LOS DOCUMENTOS SIN LAS FRECUENCIAS
        for (ListadoDocumentos d : documentosConFrecuencias) {
            resultados.add(d.documentos);
            System.out.println("Documentos: " + d.documentos.getTitulo());
        }
        
        //RETORNANDO DOCUMENTOS
        System.out.println("Retornando Documentos");
        return resultados;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }
}
