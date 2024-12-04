/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arrayss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n----- Student Management Menu -----");
        System.out.println("1. Add student(s)");
        System.out.println("2. Edit student");
        System.out.println("3. Delete student");
        System.out.println("4. Search student");
        System.out.println("5. Sort students");
        System.out.println("6. Display all students");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Thêm sinh viên không giới hạn, nhấn "q" để thoát
    public void addStudent() {
        while (true) {
            System.out.print("Enter 'q' to stop adding students or any other key to continue: ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
            int id = inputStudentId();
            String name = inputStudentName();
            double mark = inputStudentMark();
            students.add(new Student(id, name, mark));
            System.out.println("Student added successfully.");
        }
    }

    public void editStudent() {
        int id = inputStudentId();
        Student student = findStudentById(id);
        if (student != null) {
            String newName = inputStudentName();
            double newMark = inputStudentMark();
            student.setName(newName);
            student.setMark(newMark);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void deleteStudent() {
        int id = inputStudentId();
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void searchStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to search.");
            return;
        }
        int id = inputStudentId();
        students.sort(Comparator.comparing(Student::getId)); // Binary search requires sorted data
        int index = Collections.binarySearch(students, new Student(id, "", 0.0), Comparator.comparing(Student::getId));
        if (index >= 0) {
            System.out.println("Student found: " + students.get(index));
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Thêm hai thuật toán sắp xếp
    public void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        System.out.println("Choose sorting method:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                bubbleSort();
                System.out.println("Students sorted using Bubble Sort:");
                displayStudents();
                break;
            case 2:
                quickSort(0, students.size() - 1);
                System.out.println("Students sorted using Quick Sort:");
                displayStudents();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Thuật toán Bubble Sort
    private void bubbleSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    // Hoán đổi vị trí sinh viên nếu cần
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Thuật toán Quick Sort
    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            // Sắp xếp phần tử bên trái và phải
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    // Hàm phân hoạch cho Quick Sort
    private int partition(int low, int high) {
        int pivot = students.get(high).getId();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (students.get(j).getId() < pivot) {
                i++;
                // Hoán đổi vị trí sinh viên
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Hoán đổi phần tử trụ với phần tử ở vị trí đúng
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    // Hiển thị danh sách sinh viên
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("ID\tName\tMark\tRank");
            for (Student student : students) {
                System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getMark() + "\t" + student.getRank());
            }
        }
    }

    // Input validation methods
    private int inputStudentId() {
        System.out.print("Enter student ID (number only): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String inputStudentName() {
        System.out.print("Enter student name (letters only): ");
        while (!scanner.hasNext("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter letters only.");
            scanner.next();
        }
        return scanner.next();
    }

    private double inputStudentMark() {
        System.out.print("Enter student mark (0-10): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid mark.");
            scanner.next();
        }
        double mark = scanner.nextDouble();
        while (mark < 0 || mark > 10) {
            System.out.println("Mark must be between 0 and 10. Please enter again.");
            mark = scanner.nextDouble();
        }
        return mark;
    }

    private Student findStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }
}
