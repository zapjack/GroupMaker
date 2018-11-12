package edu.oaklandcc.myapplication;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ArrayAdapter<String> adapter;
    private static final String TAG = "*** AJ ***";

    ShowGroups listener;

    ListView listView;

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
        listView = view.findViewById(R.id.studentList);

        registerForContextMenu(listView);

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, StudentGroup.names);
        listView.setAdapter(adapter);

        Button generate = view.findViewById(R.id.generate);
        generate.setOnClickListener(
                event -> {
                    Spinner spinner = view.findViewById(R.id.spinner);
                    int groupSize = Integer.parseInt((String) spinner.getSelectedItem());
                    //StudentGroup.makeGroups(groupSize, Names.names);

                    StudentGroup.makeDisplayGroup(groupSize);

                    listener.showGroups(groupSize);
                }
        );
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                    long id) {
                String s = (String) adapter.getItem(position);
                Toast.makeText(view.getContext(), position + ": "+ s, Toast.LENGTH_LONG).show();
            }
        }); */
    }

    private void loadRoster() {
        Scanner scan;

        // Singleton pattern...
        if (StudentGroup.names == null) {
            StudentGroup.names = new ArrayList<String>();

            try {
                Resources res = getResources();
                InputStream is = res.openRawResource(R.raw.roster);
                scan = new Scanner(is);

                while (scan.hasNext()) {
                    String s = scan.nextLine();
                    StudentGroup.names.add(s);
                    Log.d(TAG, "READ: " + s);
                }
                scan.close();

                Collections.sort(StudentGroup.names);
            } catch (Exception e) {
                Log.d(TAG, "Exception: " + e);
            }
        }
    }

    private void saveRoster() {
        FileInputStream fis;
        Scanner scan;

        StudentGroup.names = new ArrayList<String>();

        try {
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.roster);
            scan = new Scanner(is);

            while (scan.hasNext()) {
                String s = scan.nextLine();
                StudentGroup.names.add(s);
                Log.d(TAG, "READ: " + s);
            }
            scan.close();

            Collections.sort(StudentGroup.names);
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
                StudentGroup.names.remove(position);
                StudentGroup.makeDisplayGroup(StudentGroup.groupSize); // Recreate with last-used group size
                adapter.notifyDataSetChanged();

                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    interface ShowGroups {
        public void showGroups(int groupSize);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ShowGroups) context;
    }
}
