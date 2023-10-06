package com.project.battlechopper.model;

import java.io.*;

public class Stage {
    
    private char[][] mapa;
    private int nfilas;
    private int ncolumnas;
    
    public Stage(String rutaMapa){
        leerMapa(rutaMapa);
    }
    public char[][] getMapa(){
        return mapa;
    }
    public int getFilas(){
        return nfilas;
    }
    public int getColumnas(){
        return ncolumnas;
    }
    
    public void leerMapa(String rutaMapa)  {

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaMapa))) {
            // Defino las dimensiones del mapa según el txt
            int nFilas = 0, nColumnas = 0;
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.length() > nColumnas) {
                    nColumnas = linea.length();
                }
                nFilas++;
            }

            mapa = new char[nFilas][nColumnas];

            nfilas = nFilas;
            ncolumnas = nColumnas;
            // Relleno la matriz
            //lector.close(); // El recurso se cierra automáticamente al salir del bloque try-with-resources

            try (BufferedReader lector2 = new BufferedReader(new FileReader(rutaMapa))) {
                int f = 0;

                while ((linea = lector2.readLine()) != null) {
                    while (linea.length() < nColumnas) {
                    linea += " "; // Agregar espacios para igualar la longitud
                    }
                    
                    for (int i = 0; i < nColumnas; i++) {
                        mapa[f][i] = linea.charAt(i);
                    }
                    f++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ImprimirMapa(){
        //Siempre y cuando el mapa se halla inicializado
        for(int i=0;i<nfilas;i++){
            for(int j=0;j<ncolumnas;j++){
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }


}
