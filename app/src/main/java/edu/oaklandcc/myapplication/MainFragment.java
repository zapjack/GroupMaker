package edu.oaklandcc.myapplication;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ArrayAdapter<String> adapter;
    private static final String TAG = "*** AJ ***";


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        registerForContextMenu(root.findViewById(R.id.studentList));
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadRoster();

        final View view = getView();
        ListView listView = view.findViewById(R.id.studentList);

        registerForContextMenu(listView);

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Names.names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                    long id) {
                String s = (String) adapter.getItem(position);
                Toast.makeText(view.getContext(), position + ": "+ s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadRoster() {
        FileInputStream fis;
        Scanner scan;

        Names.names = new ArrayList<String>();

        try {
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.roster);
            scan = new Scanner(is);

            while (scan.hasNext()) {
                String s = scan.nextLine();
                Names.names.add(s);
                Log.d(TAG, "READ: " + s);
            }
            scan.close();

            Collections.sort(Names.names);
        }
        catch (Exception e) {
            Log.d(TAG, "Exception: " + e);
        }
    }

    private void saveRoster() {
        FileInputStream fis;
        Scanner scan;

        Names.names = new ArrayList<String>();

        try {
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.roster);
            scan = new Scanner(is);

            while (scan.hasNext()) {
                String s = scan.nextLine();
                Names.names.add(s);
                Log.d(TAG, "READ: " + s);
            }
            scan.close();

            Collections.sort(Names.names);
        }
        catch (Exception e) {
            Log.d(TAG, "Exception: " + e);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.id.deleteItem, Menu.NONE, "Delete Student");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.deleteItem:
                // View view = getView();
                // Toast.makeText(view.getContext(), "Deleting at pos: " + position, Toast.LENGTH_LONG).show();
                Names.names.remove(position);
                adapter.notifyDataSetChanged();

                return true;
        }
        return super.onContextItemSelected(item);
    }

    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_frag_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteItem:

                return true;
        }
        return super.onContextItemSelected(item);
    }
    */
}