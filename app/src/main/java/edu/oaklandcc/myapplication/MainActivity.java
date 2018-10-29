package edu.oaklandcc.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "*** AJ ***";

    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View fragmentContainer = findViewById(R.id.main_container);
        if (fragmentContainer != null) {
            MainFragment frag = new MainFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_container, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    /*
    onClick for a button in the fragment is getting handled in the parent activity.
    What about list events in the fragment - where are those events getting handled?
     */

    public void onGenerate(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        int groupSize = Integer.parseInt((String) spinner.getSelectedItem());
        //StudentGroup.makeGroups(groupSize, Names.names);

        StudentGroup.makeDisplayGroup(groupSize);

        FrameLayout sideContainer = findViewById(R.id.side_container); // only in the large display

        if (sideContainer == null) {
            Intent intent = new Intent(this, GroupsActivity.class);
            intent.putExtra("groupSize", groupSize);
            startActivity(intent);
        }
        else {
            // have to replace 2nd time onward
            GroupsFragment frag = new GroupsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.side_container, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(getApplicationContext(), "ADD", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
