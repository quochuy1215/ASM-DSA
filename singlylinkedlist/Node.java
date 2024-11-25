/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlylinkedlist;

/**
 *
 * @author Admin
 */
public class Node {
    
    Student info;
    Node next;

    public Node(Student info, Node next) {
        this.info = info;
        this.next = next;
    }

    public Node(Student info) {
        this(info, null);
    }

    public void print() {
        info.printInfo();
    }

    
}


