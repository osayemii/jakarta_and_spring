package com.api.studentApi.model;

public class Student {
    private int id;
    private String name;
    private String programme;

    public Student(){}

    public Student(int id, String name, String programme) {
        this.id = id;
        this.name = name;
        this.programme = programme;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }
}
