/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlylinkedlist;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Main {
 static Scanner scanner = new Scanner(System.in);

    // Function to get student input from the keyboard
    public static Student inputStudent() {
        System.out.print("Enter student ID: ");
        int id = getValidID();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student name: ");
        String name = getValidName();
        System.out.print("Enter student mark (0-10): ");
        double mark = getValidMark();
        return new Student(id, name, mark);
    }

    // Function to get a valid student ID
    public static int getValidID() {
        int id;
        while (true) {
            try {
                id = scanner.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.print("Invalid ID. Please enter a positive number: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
        return id;
    }

    // Function to get a valid student name (no numbers or special characters)
    public static String getValidName() {
        String name;
        while (true) {
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.print("Invalid name. Please enter letters only: ");
            }
        }
        return name;
    }

    // Function to get valid marks (only between 0 and 10)
    public static double getValidMark() {
        double mark;
        while (true) {
            try {
                mark = scanner.nextDouble();
                if (mark >= 0 && mark <= 10) {
                    break;
                } else {
                    System.out.print("Invalid mark. Please enter a mark between 0 and 10: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
        return mark;
    }

    // Add students continuously until the user decides to stop
    public static void addStudents(Mylist ml) {
        boolean keepAdding = true;
        while (keepAdding) {
            Student student = inputStudent();
            ml.add(student);
            System.out.println("Student added successfully!");

            // Ask if the user wants to add another student or stop
            System.out.print("Do you want to add another student? (y/n): ");
            String choice = scanner.next().toLowerCase();
            if (!choice.equals("y")) {
                keepAdding = false; // Stop adding if the user enters anything other than 'y'
            }
            scanner.nextLine(); // Consume any leftover input
        }
    }

    // Function to validate and get menu choice from the user
    public static int getMenuChoice() {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= 6) {  // Valid menu options from 0 to 6
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 0 and 6: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear invalid input
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        Mylist ml = new Mylist();
        int choice;
        do {
            System.out.println("\n1. Add students");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Search student");
            System.out.println("5. Sort students by mark and display");
            System.out.println("6. Show all students");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = getMenuChoice();  // Get valid menu choice

            switch (choice) {
                case 1:
                    addStudents(ml); // Add students
                    break;
                case 2:
                    System.out.print("Enter the student ID to edit: ");
                    ml.edit(getValidID()); // Edit student
                    break;
                case 3:
                    System.out.print("Enter the student ID to delete: ");
                    ml.delete(getValidID()); // Delete student
                    break;
                case 4:
                    System.out.print("Enter the student ID to search: ");
                    Student foundStudent = ml.search(getValidID());
                    if (foundStudent != null) {
                        foundStudent.printInfo(); // Display student info if found
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    ml.sortStudents(); // Sort students and display sorted list
                    break;
                case 6:
                    System.out.println("List of all students:");
                    ml.traverse(); // Show all students
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}