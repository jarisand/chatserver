package com.example.jari.chat;


import android.app.Activity;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;



public class MainActivity extends Activity {

    EditText editInput;
    TextView textView;
    Socket socket;


    private android.os.Handler uiHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                TextView textView = (TextView) findViewById(R.id.textView);
                //textView.setText((String) msg.obj);
                String input = (String)msg.obj;
                textView.append(input + "\n");
                //editInput.setText(null);

                Toast.makeText(MainActivity.this, R.string.handler, Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        socket = new Socket();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Receiver c = new Receiver(uiHandler, socket);
        Thread t = new Thread(c);

        editInput = (EditText) findViewById(R.id.editInput);
        textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.sendButton);


        Toast.makeText(MainActivity.this, R.string.menitry, Toast.LENGTH_SHORT).show();

        t.start();
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            try {
                //Socket socket = null;
                String input = editInput.getText().toString();
                textView.append(input + "\n");
                Thread sender = new Thread(new MsgSender(socket, input));
                sender.start();
                //Toast.makeText(MainActivity.this, "klikki threadin jälkee", Toast.LENGTH_SHORT).show();
                editInput.setText(null);
            }
            catch(Exception e){
                System.out.println(e);
            }

            }


        });
    };
}