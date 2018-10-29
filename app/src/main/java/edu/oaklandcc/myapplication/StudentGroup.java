package edu.oaklandcc.myapplication;

import java.util.ArrayList;
import java.util.Collections;

public class StudentGroup {

    // Static members
    // EXCLUDE THIS!

    public static ArrayList<String> names;
    public static int groupSize = 0;
    public static ArrayList<StudentGroup> displayGroup = null;
    public static void makeDisplayGroup(int groupSize, ArrayList<String> roster) {
        StudentGroup.groupSize = groupSize;
        displayGroup = new ArrayList<>();
        Collections.shuffle(roster);
        int numGroups = roster.size() / groupSize;

        int j = 0;
        for (int i = 0; i < numGroups && j < roster.size(); i++) {
            StudentGroup group = new StudentGroup();
            for (int k = 0; k < groupSize && j < roster.size(); k++) {
                group.addMember(roster.get(j));
                j++;
            }
            displayGroup.add(group);
        }
    }

    public static String getGroupsAsString() {
        String s = "";
        int i = 0;

        for (StudentGroup g : StudentGroup.displayGroup) {
            s += "Team: " + i++;
            s += g.toString() + "\n";
        }

        return s;
    }

    // Instance members

    private ArrayList<String> members;

    public StudentGroup() {
        members = new ArrayList<String>();
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void addMember(String student) {
        this.members.add(student);
    }

    public String getMember(int position) {
        return members.get(position);
    }

    @Override
    public String toString() {
        String s = "";
        for (String member : members)
            s += member + "\n";
        return s;
    }
}
