package com.busquedas.avl.indexacion;
import com.busquedas.avl.arbolAvl.ArbolAvl;
import com.busquedas.avl.modelo.DocumentoInfo;
import com.busquedas.avl.Utilidades.LectorArchivos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Indexer {
    private final ArbolAvl arbolAvl;
    private final OmitirPalabras omitirPalabras;
    private File[] documentos;
    private List<DocumentoInfo> listaDocumentos;

    public Indexer(ArbolAvl arbolAvl) {
        this.arbolAvl = arbolAvl;
        this.omitirPalabras = new OmitirPalabras();
        this.listaDocumentos = new ArrayList<>();
    }

    public void obtenerDocumentos(File carpeta) {
        listaDocumentos.clear();
        arbolAvl.reiniciar();

        if(carpeta.listFiles() == null){
            System.out.println("No tiene archivos la carpeta");
            return;
        }
        
        //GUARDA EL DOCUMENTO EN UN ARRAY DE DOCUMENTOS CUANDO EL DOCUMENTO SEA TIPO DOCX
        documentos = carpeta.listFiles((directorio, nombre) -> nombre.endsWith(".docx")); 

        if (documentos == null) {
            System.out.println("No se encontraron documentos .docx");
            return;
        }

        for (File documento : documentos) { //CADA DOCUMENTO SERA PASADO POR UN INDEXER
            indexarDocumentoUnico(documento);
        }
    }

    private void indexarDocumentoUnico(File documento) {
        String contenido = LectorArchivos.leerDoc(documento);
        if (contenido == null || contenido.isEmpty()){
            return;
        }
            
        String titulo = documento.getName();
       
        String ruta;
        try {
            ruta = documento.getCanonicalPath().toLowerCase().replace("\\", "/");
        } catch (IOException e) {
            System.err.println("No se pudo obtener la ruta del documento: " + documento.getName());
            return;
        }
        
        String descripcion = extraerDescripcion(contenido);

        DocumentoInfo doc = new DocumentoInfo(titulo, ruta, descripcion);
        
        listaDocumentos.add(doc);
        
        String[] palabrasAlfanumericos = contenido.toLowerCase().split("[^\\p{L}\\p{N}]+");;
        

        //RECORREMOS POR TODAS LAS PALABRAS DEL DOCUMENTO
        for (String palabra : palabrasAlfanumericos) {
            //SI LA PALABRA NO ES "EL LA LOS" LA INGRESAMOS AL ARBOL AVL
            if (!omitirPalabras.esPalabraOmitida(palabra) && palabra.length() > 1) {
                System.out.println("Insertando palabra: " + palabra + " del documento: " + doc.getTitulo());
                arbolAvl.insertar(palabra, doc);
            }
        }
    }

    private String extraerDescripcion(String texto) {
        return texto.length() > 120 ? texto.substring(0, 120) + "..." : texto;
    }

    public List<DocumentoInfo> getDocumentos(){
       return listaDocumentos;
    }
}
