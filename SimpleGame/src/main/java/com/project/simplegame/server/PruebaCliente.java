package com.project.simplegame.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author walterrg
 */
public class PruebaCliente {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1",8188);
            try {
                InputStream secuenciaDeEntrada = s.getInputStream();
                OutputStream secuenciaDeSalida = s.getOutputStream();
                PrintWriter pw = new PrintWriter(secuenciaDeSalida, true);
                Scanner in = new Scanner(secuenciaDeEntrada);
                Scanner scan = new Scanner(System.in);

                new Thread(() -> {
                    while(scan.hasNextLine()) {
                        String line = scan.nextLine();
                        pw.println(line);
                    }
                }).start();

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                }
            } finally{
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
