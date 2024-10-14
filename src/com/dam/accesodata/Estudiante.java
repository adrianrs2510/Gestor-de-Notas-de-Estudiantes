package com.dam.accesodata;

public class Estudiante {
    private String nombre;
    private double nota;

    public Estudiante(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return nombre + "," + nota;
    }

    public static Estudiante fromString(String linea) {
        String[] partes = linea.split(",");
        String nombre = partes[0];
        double nota = Double.parseDouble(partes[1]);
        return new Estudiante(nombre, nota);
    }
}
