package edu.oaklandcc.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends ListFragment {
    public GroupsFragment() {
        // Required empty public constructor
    }

    /*
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_groups, container, false);
        }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        String[] names = new String[StudentGroup.groups.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = StudentGroup.groups[i].toString();
        }
        */
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                names);
                */

        if (StudentGroup.groupSize == 2) {
            TwoAdapter adapter = new TwoAdapter(inflater.getContext(), StudentGroup.displayGroup);
            setListAdapter(adapter);
        }
        else if (StudentGroup.groupSize == 3) {
            ThreeAdapter adapter = new ThreeAdapter(inflater.getContext(), StudentGroup.displayGroup);
            setListAdapter(adapter);
        }
        else {
            FourAdapter adapter = new FourAdapter(inflater.getContext(), StudentGroup.displayGroup);
            setListAdapter(adapter);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
    /*    if (mListener != null) {
            mListener.onFragmentInteraction(id);
        } */
    }
}
