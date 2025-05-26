package student;

import java.util.HashMap;
import java.util.Map;

public class LinkedList {
	static class Node {
		private Node next;
		public Student data;
		public Node (Student data) {
			this.data = data;
			this.next = null;
		}
		
	}
	static class Student{
		private int rollNo;
		private String name;
		private int std;
		private int marks;
		
		public Student(int rollNo, String name, int std, int marks) {
			this.rollNo = rollNo;
			this.name = name;
			this.std = std;
			this.marks = marks;
		}

		@Override
		public String toString() {
			return "Student [rollNo=" + rollNo + ", name=" + name + ", std=" + std + ", marks=" + marks + "]";
		}
	}
	Map<Integer, Integer> stdCount = new HashMap<>();
	
	private Node head;
	private Node tail;
	public LinkedList() {
		head = tail= null;
	}
	
	public void addFirst(Student s) {
		Node newnode = new Node(s);
		if(s.std >=5 && s.std <=8 ) {
			if(head == null) {
				head = tail = newnode;
				newnode.next = newnode;
			}
			else {
				newnode.next = head;
				head = newnode;
			}
			stdCount.put(s.std, stdCount.getOrDefault(s.std, 0)+1);
			System.out.println(s.toString()+ " Student added.");
		}
		else {
			System.out.println("Our school is only of 5 to 8 std");
			return;
		}

	}
	
	public void addLast(Student s) {
		Node newnode = new Node(s);
		if(s.std >=5 && s.std <=8 ) {
			if(head == null) {
				head = tail = newnode;
				newnode.next = newnode;
			}
			else {
				tail.next = newnode;
				tail = newnode;
			}
			stdCount.put(s.std, stdCount.getOrDefault(s.std, 0)+1);
			System.out.println(s.toString()+ " Student added.");
		}
		else {
			System.out.println("Our school is only of 5 to 8 std");
			return;
		}

	}
	
	
	public void deleteStudent(int roll, int std) {
	    if (head == null) {
	        System.out.println("List is empty");
	        return;
	    }

	    if (head == tail && head.data.rollNo == roll && head.data.std == std) {
	        head = tail = null;
	        System.out.println("Student deleted.");
	        stdCount.put(std, stdCount.getOrDefault(std, 1) - 1);
	        return;
	    }

	    if (head.data.rollNo == roll && head.data.std == std) {
	        head = head.next;
	        tail.next = head;  
	        stdCount.put(std, stdCount.getOrDefault(std, 1) - 1);
	        System.out.println("Student deleted.");
	        return;
	    }

	    Node trav = head;
	    do {
	        if (trav.next.data.rollNo == roll && trav.next.data.std == std) {
	            if (trav.next == tail) {
	                tail = trav; 
	            }
	            trav.next = trav.next.next;
	            stdCount.put(std, stdCount.getOrDefault(std, 1) - 1);
	            System.out.println("Student deleted.");
	            return;
	        }
	        trav = trav.next;
	    } while (trav != head);

	    System.out.println("No student found!");
	}
	
	
	
	public void updateMarks(int roll, int std, int marks) {
	    if (head == null) {
	        System.out.println("List is empty!");
	        return;
	    }

	    Node trav = head;
	    boolean updated = false;

	    do {
	        if (trav.data.rollNo == roll && trav.data.std == std) {
	            System.out.println("Marks updated for:");
	            System.out.println("Before: " + trav.data.toString());
	            trav.data.marks = marks;
	            System.out.println("After : " + trav.data.toString());
	            updated = true;
	            break;
	        }
	        trav = trav.next;
	    } while (trav != head);

	    if (!updated) {
	        System.out.println("No record found!");
	    }
	}

	
	
	public void printAbove80() {
		if(head == null) {
			System.out.println("List is empty! ");
			return;
		}
		else {
			Node trav = head;
			do {
				if(trav.data.marks > 80) {
					System.out.println(trav.data.toString());
				}
			}while(trav!=head);
		}
	}
	
	public void studenStandard(int std) {
		if(head == null) {
			System.out.println("List is empty!");
			return;
		}
		if(std < 5 && std > 8) {
			System.out.println("Invalid standard! ");
			return;
		}
		else {
			Node trav = head;
			do {
				if(trav.data.std == std) {
					System.out.println(trav.data.toString());
				}
				trav = trav.next;
			}while(trav!=head);
		}
	}
	
	
	public void stdWiseCount(){
	    System.out.println("Standard-wise Student Count:");
	    for (Map.Entry<Integer, Integer> entry : stdCount.entrySet()) {
	        System.out.println("Standard " + entry.getKey() + ": " + entry.getValue() + " student(s)");
	    }
	}

}


	
	
	


