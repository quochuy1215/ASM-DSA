/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlylinkedlist;

import java.util.Scanner;


/**
 *
 * @author Admin
 */
public class Mylist {
     Node head, tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Student x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }

    public void traverse() {
        Node p = head;
        while (p != null) {
            p.print();
            p = p.next;
        }
    }

    public Student search(int id) {
        Node p = head;
        while (p != null) {
            if (p.info.getID() == id) {
                return p.info;
            }
            p = p.next;
        }
        return null;
    }

    public void edit(int id) {
        traverse();
        Student student = search(id);
        if (student != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new name: ");
            String newName = Main.getValidName();
            double newMark = Main.getValidMark();
            student.setName(newName);
            student.setMark(newMark);
            System.out.println("Student information updated.");
        } else {
            System.out.println("Student not found. Please enter a valid ID.");
            edit(Main.getValidID());
        }
    }

    public void delete(int id) {
        traverse();
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        if (head.info.getID() == id) {
            head = head.next;
            System.out.println("Student with ID " + id + " has been deleted.");
            return;
        }
        Node prev = null, p = head;
        while (p != null && p.info.getID() != id) {
            prev = p;
            p = p.next;
        }
        if (p == null) {
            System.out.println("Student not found. Please enter a valid ID.");
            delete(Main.getValidID());
        } else {
            prev.next = p.next;
            if (p == tail) tail = prev;
            System.out.println("Student with ID " + id + " has been deleted.");
        }
    }

    public void bubbleSort() {
        if (isEmpty() || head.next == null) return;
        
        Node current, index;
        Student temp;
        for (current = head; current != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.info.getMark() < index.info.getMark()) {
                    temp = current.info;
                    current.info = index.info;
                    index.info = temp;
                }
            }
        }
    }
    
    public Node partition(Node low, Node high) {
    double pivot = high.info.getMark();
    Node i = low;
    Node j = low;

    while (j != high) {
        if (j.info.getMark() > pivot) {
            // Swap the data of i and j
            Student temp = i.info;
            i.info = j.info;
            j.info = temp;

            i = i.next;
        }
        j = j.next;
    }

    // Swap the data of i and high (pivot)
    Student temp = i.info;
    i.info = high.info;
    high.info = temp;

    return i;
}

    public void quickSort(Node low, Node high) {
    if (low != null && high != null && low != high && low != high.next) {
        Node pivot = partition(low, high);
        quickSort(low, getPrevious(pivot));  // Sort before pivot
        quickSort(pivot.next, high);         // Sort after pivot
    }
}

// Helper method to get the previous node of a given node
    private Node getPrevious(Node node) {
    Node temp = head;
    while (temp != null && temp.next != node) {
        temp = temp.next;
    }
    return temp;
}

   

   public void sortStudents() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("    Choose sorting method: ");
    System.out.println("1. Bubble Sort");
    System.out.println("2. Quick Sort");
    int choice = scanner.nextInt();

    switch (choice) {
        case 1:
            bubbleSort();
            System.out.println("List sorted using Bubble Sort:");
            break;
        case 2:
            quickSort(head, tail);
            System.out.println("List sorted using Quick Sort:");
            break;
        default:
            System.out.println("Invalid choice.");
            return;
        }
        traverse();
   }

      // Display the sorted list after sorting
}
