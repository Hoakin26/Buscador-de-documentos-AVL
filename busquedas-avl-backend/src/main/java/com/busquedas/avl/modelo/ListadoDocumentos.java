package com.busquedas.avl.modelo;
import java.util.Objects;

public class ListadoDocumentos {

    public DocumentoInfo documentos;
    public int frecuenciaPalabra;

    public ListadoDocumentos(DocumentoInfo documentos, int frecuenciaPalabra) {
        this.documentos = documentos;
        this.frecuenciaPalabra = frecuenciaPalabra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListadoDocumentos that = (ListadoDocumentos) o;
        return Objects.equals(documentos, that.documentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentos);
    }
}
