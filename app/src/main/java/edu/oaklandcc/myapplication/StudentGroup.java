package edu.oaklandcc.myapplication;

import java.util.ArrayList;
import java.util.Collections;

public class StudentGroup {

    // Static members
    // EXCLUDE THIS!

    public static final int DEFAULT_GROUP_SIZE = 2;

    // raw list of names - not in groups yet
    public static ArrayList<String> names;
    public static int groupSize = DEFAULT_GROUP_SIZE;

    // the names in groups
    public static ArrayList<StudentGroup> displayGroup = null;

    public static void makeDisplayGroup(int groupSize) {
        StudentGroup.groupSize = groupSize;
        ArrayList<String> tempList = new ArrayList<>();
        for (String s : names) tempList.add(s);

        displayGroup = new ArrayList<>();
        Collections.shuffle(tempList);
        int numGroups = tempList.size() / groupSize;
        int numLeftover = tempList.size() % groupSize;

        StudentGroup last = null;

        int j = 0;
        for (int i = 0; i < numGroups && j < tempList.size(); i++) {
            StudentGroup group = new StudentGroup();
            last = group;
            for (int k = 0; k < groupSize && j < tempList.size(); k++) {
                group.addMember(tempList.get(j));
                j++;
            }
            displayGroup.add(group);
        }

        if (numLeftover > 0) {
            StudentGroup group = new StudentGroup();
            if (numLeftover == 1 && groupSize > 2) {
                group.addMember(last.deleteMember());
                last.addMember("");
            }

            int m = 0;
            while (m < numLeftover) {
                group.addMember(tempList.get(j++));
                m++;
            }

            while (m < groupSize) {
                group.addMember("");
                m++;
            }
            displayGroup.add(group);
        }
    }

    public static String getGroupsAsString() {
        String s = "";
        int i = 0;

        for (StudentGroup g : StudentGroup.displayGroup) {
            s += "Team: " + i++ +"\n";
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

    public String deleteMember() {
        return members.remove(0);
    }

    @Override
    public String toString() {
        String s = "";
        for (String member : members)
            s += member + "\n";
        return s;
    }
}
