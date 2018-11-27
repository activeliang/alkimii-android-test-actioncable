package com.alkimii.test.testactioncable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    Button test;
    TestActionCable testActionCable=new TestActionCable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=(Button)findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    testActionCable.create();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
