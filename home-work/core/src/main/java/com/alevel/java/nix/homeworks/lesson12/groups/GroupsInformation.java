package com.alevel.java.nix.homeworks.lesson12.groups;

public class GroupsInformation {
    private Group[] groups;

    public GroupsInformation(Group[] groups) {
        this.groups = groups;
    }

    public void showContractStudents(final int groupNumber) {
        if (groupNumber >= groups.length || groupNumber < 0) {
            System.out.println("No such group!");
            return;
        }
        for (Student student : groups[groupNumber]) {
            if (student instanceof ContractStudent) {
                System.out.println(student.toString());
            }
        }
    }
}
