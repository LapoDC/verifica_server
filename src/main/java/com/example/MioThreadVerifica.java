package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThreadVerifica extends Thread {
    Socket s = new Socket();

    public MioThreadVerifica(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String stringaRicevuta = "";
            Random random = new Random();
            int NumeroCasuale = random.nextInt(100);
            System.out.println(NumeroCasuale);
           
            while (true) {
               
                if(stringaRicevuta.equals("y"))
                NumeroCasuale = random.nextInt(100);
                stringaRicevuta = in.readLine();
                System.out.println("La stringa ricevuta: " + stringaRicevuta);
                int numeroRicevuto = Integer.parseInt(stringaRicevuta);

                if (numeroRicevuto > NumeroCasuale) {
                    if (numeroRicevuto > 100) {
                        System.out.println("numero fuori range ");
                        out.writeBytes("!" + "\n");
                    } else {
                        System.out.println("minore");
                        out.writeBytes("<" + "\n");
                    }
                }

                if (numeroRicevuto < NumeroCasuale) {
                    if (numeroRicevuto < 0) {
                        System.out.println("numero fuori range ");
                        out.writeBytes("!" + "\n");
                    } else {
                        System.out.println("maggiore");
                        out.writeBytes(">" + "\n");
                    }
                }
                if (numeroRicevuto == NumeroCasuale){
                    out.writeBytes("=" + "\n");
                   
                stringaRicevuta = in.readLine();
                if(stringaRicevuta.equals("y"))
                NumeroCasuale = random.nextInt(100);

                if (stringaRicevuta.equals("n"))
                    break;
                
                if (stringaRicevuta.equals("errore")) {
                    System.out.println("comando non riconoscuto chiusura collegamento in corso...");
           
                    break;
                }

            }

        }
          

            s.close();
        

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
