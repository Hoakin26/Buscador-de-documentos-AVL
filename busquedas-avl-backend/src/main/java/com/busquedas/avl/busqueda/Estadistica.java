package com.busquedas.avl.busqueda;
public class Estadistica {
    private int busquedasTotales = 0;
    private long tiempoTotal = 0;
    private int comparacionesTotales = 0;

    public void aggBusqueda(long tiempoTardado, int comparaciones) {
        busquedasTotales++;
        tiempoTotal += tiempoTardado;
        comparacionesTotales += comparaciones;
    }

    public double getTiempoPromedioMilisegundos() {
        return (busquedasTotales == 0) ? 0 : Math.round(((double) tiempoTotal / busquedasTotales) * 100.0) / 100.0;
    }

    public double getComparacionesPromedio() {
        return (busquedasTotales == 0) ? 0 : Math.round(((double) comparacionesTotales / busquedasTotales) * 100.0) / 100.0;
    }

    public int getBusquedasTotales() {
        return busquedasTotales;
    }
}
