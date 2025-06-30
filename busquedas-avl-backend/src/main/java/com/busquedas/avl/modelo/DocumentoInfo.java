package com.busquedas.avl.modelo;
import java.util.Objects;

public class DocumentoInfo {
    private String titulo;
    private String ruta;
    private String descripcion;

    public DocumentoInfo(String titulo, String ruta, String descripcion) {
        this.titulo = titulo;
        this.ruta = ruta;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getRuta() {
        return ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof DocumentoInfo)){
            return false;
        }

        DocumentoInfo other = (DocumentoInfo) obj;
        return Objects.equals(titulo, other.titulo) && Objects.equals(ruta, other.ruta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruta);
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nRuta: " + ruta + "\nDescripción: " + descripcion;
    }
}
