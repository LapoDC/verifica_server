package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s = new Socket();

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String stringaRicevuta = "";
            while (true) {
                stringaRicevuta = in.readLine();
                System.out.println("La stringa ricevuta: " + stringaRicevuta);
                if (stringaRicevuta.equals("!"))
                    break;
                String stringaDaModificare = stringaRicevuta;
                stringaRicevuta = in.readLine();
                switch ((stringaRicevuta)) {
                    case "M":

                    stringaDaModificare = stringaDaModificare.toUpperCase();
                        break;

                    case "m":
                        stringaDaModificare = stringaDaModificare.toLowerCase();
                        break;

                    case "R":
                        char ch;
                        String invertita = "";
                        for (int i = 0; i < stringaDaModificare.length(); i++) {
                            ch = stringaDaModificare.charAt(i);
                            invertita = ch + invertita;
                        }
                        stringaDaModificare = invertita;
                        break;

                    case "C":
                        stringaDaModificare = stringaDaModificare.length() + "";
                        break;

                    default:
                        stringaDaModificare = "La funzione inserita non esiste";
                }
                out.writeBytes(stringaDaModificare + "\n");
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
