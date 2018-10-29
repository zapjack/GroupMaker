package edu.oaklandcc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class FourAdapter extends ArrayAdapter<StudentGroup> {
    public FourAdapter(Context context, ArrayList<StudentGroup> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StudentGroup group = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.four_person_team, parent, false);
        }
        // Lookup view for data population
        TextView teamNumber = (TextView) convertView.findViewById(R.id.teamNumberSize4);
        TextView textMember1 = (TextView) convertView.findViewById(R.id.member1Size4);
        TextView textMember2 = (TextView) convertView.findViewById(R.id.member2Size4);
        TextView textMember3 = (TextView) convertView.findViewById(R.id.member3Size4);
        TextView textMember4 = (TextView) convertView.findViewById(R.id.member4Size4);
        // Populate the data into the template view using the data object

        teamNumber.setText("Team: " + position);
        textMember1.setText(group.getMember(0));
        textMember2.setText(group.getMember(1));
        textMember3.setText(group.getMember(2));
        textMember4.setText(group.getMember(3));

        // Return the completed view to render on screen
        return convertView;
    }
}
