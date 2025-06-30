package com.busquedas.avl.Utilidades;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class LectorArchivos {
    //ESTATICO PARA LLARLO SIN INSTANCIAR LA CLASE
    public static String leerDoc(File archivo) {
        
        StringBuilder txt = new StringBuilder();
        //ABRE EL ARCHIVO PARA LECTURA BINARIA
        try (FileInputStream flujoArchivo = new FileInputStream(archivo);
                
            //OBJETO DE APACHE POI QUE PERMITE LEER LOS DOCUMENTOS
            XWPFDocument docuemnto = new XWPFDocument(flujoArchivo)) {
            
            //OBTIENE TODOS LOS PARRAFOS DEL DOCUMENTO
            List<XWPFParagraph> parrafos = docuemnto.getParagraphs();
            
            for (XWPFParagraph parrafo : parrafos) {
                //AGREGA EL TEXTO AL STRINGBUILDER
                txt.append(parrafo.getText()).append(" ");
            }
        } catch (Exception e) {
            System.err.println("Error al leer el documento: " + archivo.getName());
            e.printStackTrace();
        }
        //RETORNA EL TEXTO SIN ESPACIO AL INICIO Y FIN
        return txt.toString().trim();
    }
}
