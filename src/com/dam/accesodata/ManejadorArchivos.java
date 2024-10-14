package com.dam.accesodata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String ARCHIVO = "resources/notas_estudiantes.txt"; // Ruta del archivo

    // Constructor que verifica si el archivo existe, si no, lo crea
    public ManejadorArchivos() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            try {
                // Crear la carpeta 'resources' si no existe
                file.getParentFile().mkdirs();

                // Crear el archivo si no existe
                file.createNewFile();
                System.out.println("El archivo " + ARCHIVO + " ha sido creado.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    // Añadir estudiante al archivo
    public void añadirEstudiante(Estudiante estudiante) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(estudiante.toString()); // Guardamos el estudiante en el formato definido en su método toString()
            bw.newLine();
            System.out.println("Estudiante añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir estudiante: " + e.getMessage());
        }
    }

    // Mostrar todos los estudiantes del archivo
    public void mostrarEstudiantes() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            System.out.println("\nLista de estudiantes:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // Mostramos cada línea (cada estudiante)
            }
        } catch (IOException e) {
            System.out.println("Error al mostrar estudiantes: " + e.getMessage());
        }
    }

    // Buscar un estudiante por nombre
    public void buscarEstudiante(String nombre) {
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Estudiante estudiante = Estudiante.fromString(linea);
                if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                    System.out.println("Estudiante encontrado: " + estudiante);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Estudiante no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar estudiante: " + e.getMessage());
        }
    }

    // Calcular la nota media de todos los estudiantes
    public void calcularMedia() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para calcular la media.");
            return;
        }

        double sumaNotas = 0;
        for (Estudiante est : estudiantes) {
            sumaNotas += est.getNota();
        }
        double media = sumaNotas / estudiantes.size();
        System.out.println("La nota media de los estudiantes es: " + media);
    }

    // Leer todos los estudiantes desde el archivo
    private List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                estudiantes.add(Estudiante.fromString(linea)); // Convertimos cada línea a un objeto Estudiante
            }
        } catch (IOException e) {
            System.out.println("Error al leer estudiantes: " + e.getMessage());
        }
        return estudiantes;
    }
}
