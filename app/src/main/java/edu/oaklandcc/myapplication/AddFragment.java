package edu.oaklandcc.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    AddingSome listener;

    Button buttonAdd;
    EditText editAdd;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddingSome) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        buttonAdd = view.findViewById(R.id.buttonAdd);
        editAdd = view.findViewById(R.id.editAdd);

        buttonAdd.setOnClickListener(
                (event)-> {
                    String name = editAdd.getText().toString().trim();
                    listener.addedSomeone(editAdd.getText().toString().trim());
                    editAdd.setText("");
                    Toast.makeText(getContext(), "Added: " + name, Toast.LENGTH_LONG).show();
                }
        );
    }

    interface AddingSome {
        public void addedSomeone(String name);
    }
}
