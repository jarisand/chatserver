package com.example.jari.chat;

import android.os.Message;
import android.util.Log;
import android.view.View;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Handler;


public class Receiver implements Runnable {

    private android.os.Handler h;
    private final static int PORT = 6677;
    private final static String HOST = "10.112.213.64";

    Socket s;


    public Receiver(android.os.Handler h, Socket s){
        this.s = s;
        this.h = h;
    }

    public Receiver(){

    }

    /*public void returnSocket(){
        return s.getLocalSocketAddress();
    }*/

    @Override
    public void run() {
        try {
            s.connect(new InetSocketAddress(HOST, PORT));
            //Scanner chat = new Scanner(System.in);
            Scanner in = new Scanner(s.getInputStream());


            while(true){
                String input  =  in.nextLine();
                Message msg = h.obtainMessage();
                msg.obj = input;
                msg.what = 0;
                h.sendMessage(msg);

            }

            // outS.println(input);
            // outS.flush();

        } catch (Exception e) {
            Log.e("ERROR", "RECEIVERISSÄ", e);
        }

    }
}

