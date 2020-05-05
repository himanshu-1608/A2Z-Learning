package com.himanshu.a2zlearning.rankings;

public class basiclist {
    private int marks;
    private String name;

    public basiclist(){}
    basiclist(int marks, String name) {
        this.marks = marks;
        this.name = name;
    }

    int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
