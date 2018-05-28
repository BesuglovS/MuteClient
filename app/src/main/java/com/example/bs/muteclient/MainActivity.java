package com.example.bs.muteclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ip;

    Button send;
    Spinner spinner;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);

        spinner = findViewById(R.id.commandSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.commands, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        ip = findViewById(R.id.ipaddr);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send(view);
            }
        });
    }

    public void Send(View v)
    {
        ArrayList<String> params = new ArrayList<>();
        params.add(ip.getText().toString());

        Integer volume = seekBar.getProgress();

        String command = "";
        String spinnerText = spinner.getSelectedItem().toString();

        switch (spinnerText) {
            case "Set":
                command = "Set " + volume;
                break;
            case "Support":
                command = "Support " + volume;
                break;
            case "Support OFF":
                command = "Support OFF";
                break;
        }

        params.add(command);

        MessageSender sender = new MessageSender();
        sender.execute(params);
    }
}
