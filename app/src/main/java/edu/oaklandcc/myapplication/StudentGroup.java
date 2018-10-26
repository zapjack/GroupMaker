package edu.oaklandcc.myapplication;

import java.util.ArrayList;
import java.util.Collections;

public class StudentGroup {
    public static StudentGroup [] groups;

    public static StudentGroup[] getGroups() {
        return groups;
    }

    // EXCLUDE THIS!

    public static void makeGroups(int groupSize, ArrayList<String> roster) {
        Collections.shuffle(roster);
        int numGroups = roster.size() / groupSize;
        groups = new StudentGroup[numGroups];

        int j = 0;
        for (int i = 0; i < numGroups && j < roster.size(); i++) {
            StudentGroup group = new StudentGroup("Team " + i);
            for (int k = 0; k < groupSize && j < roster.size(); k++) {
                group.addMember(roster.get(j));
                j++;
            }
            groups[i] = group;
        }
    }

    private String name;
    private ArrayList<String> members;

    public StudentGroup(String name) {
        this.name = name;
        members = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void addMember(String student) {
        this.members.add(student);
    }

    @Override
    public String toString() {
        return name + ": " + members;
    }
}
