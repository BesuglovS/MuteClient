package com.example.bs.muteclient;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MessageSender extends AsyncTask<ArrayList<String>, Void, Void> {

    Socket s;
    PrintWriter pw;

    @Override
    protected Void doInBackground(ArrayList<String>... strings) {
        ArrayList<String> params = strings[0];
        try
        {
            s = new Socket(params.get(0), 5000);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(params.get(1));
            pw.flush();
            pw.close();
            s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
