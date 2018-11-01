package edu.oaklandcc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddActivity extends AppCompatActivity implements AddFragment.AddingSome {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public void addedSomeone(String name) {
        StudentGroup.names.add(name);
    }
}
