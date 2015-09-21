package com.example.jari.chat;


import android.app.Activity;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class MsgSender extends Activity implements Runnable {

    private android.os.Handler h;
    Socket s;
    String message;


    public MsgSender(Socket s, String message) {
        this.s = s;
        this.message = message;
    }



        @Override
        public void run() {

            try {
                if (s != null) {
                    //Toast.makeText(MsgSender.this, "sender thread toast", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "sender run pyörähdys");
                    Message msg;
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    out.write(message);
                    out.close();
                } else {
                    //Toast.makeText(MsgSender.this, "socket tyhjä", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "sender run else error");

                }
            } catch (Exception e) {
                Log.e("ERROR", "SENDERISSÄ", e);

            }

        }



}

