package edu.oaklandcc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ThreeAdapter extends ArrayAdapter<StudentGroup> {
    public ThreeAdapter(Context context, ArrayList<StudentGroup> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StudentGroup group = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.three_person_team, parent, false);
        }
        // Lookup view for data population
        TextView teamNumber = (TextView) convertView.findViewById(R.id.teamNumberSize3);
        TextView textMember1 = (TextView) convertView.findViewById(R.id.member1Size3);
        TextView textMember2 = (TextView) convertView.findViewById(R.id.member2Size3);
        TextView textMember3 = (TextView) convertView.findViewById(R.id.member3Size3);
        // Populate the data into the template view using the data object

        teamNumber.setText("Team: " + position);
        textMember1.setText(group.getMember(0));
        textMember2.setText(group.getMember(1));
        textMember3.setText(group.getMember(2));
        // Return the completed view to render on screen
        return convertView;
    }
}

