/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arrayss;

/**
 *
 * @author Admin
 */
public class Student {
    private int id;
    private String name;
    private double mark;
    private String rank;

    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        setRank();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
        setRank();
    }

    public String getRank() {
        return rank;
    }

    private void setRank() {
        if (mark < 5.0) rank = "Fail";
        else if (mark < 6.5) rank = "Medium";
        else if (mark < 7.5) rank = "Good";
        else if (mark < 9.0) rank = "Very Good";
        else rank = "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Mark: " + mark + ", Rank: " + rank;
    }
}


