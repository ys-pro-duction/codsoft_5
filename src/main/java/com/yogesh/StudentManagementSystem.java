package com.yogesh;

import java.util.Scanner;

public class StudentManagementSystem {
    private final DatabaseManagement dm = new DatabaseManagement();

    public StudentManagementSystem() {

    }

    public void addStudent() {
        System.out.println("\nEnter student details");
        System.out.println("---------------------");
        Student student = new Student();
        inputRollNumber(student);
        inputName(student);
        inputGrade(student);

        if (isStudentRollNumberExist(student)) {
            System.out.println("Roll number " + student.rollNumber + " already exits.");
        } else {
            if (dm.insert(student)) {
                System.out.println("Student added");
            }
        }
    }

    public void updateStudent() {
        System.out.println("\nUpdate student details");
        System.out.println("---------------------");
        Student student = new Student();
        System.out.print("for ");
        inputRollNumber(student);
        System.out.print("new ");
        inputName(student);
        System.out.print("new ");
        inputGrade(student);
        if (isStudentRollNumberExist(student)) {
            if (dm.update(student)) {
                System.out.println("Student details updated");
            }
        } else {
            System.out.println("Roll number " + student.rollNumber + " not found.");
        }
    }

    public void removeStudent() {
        System.out.println("Enter student roll number to be deleted");
        System.out.println("---------------------");
        Student student = new Student();
        inputRollNumber(student);
        if (isStudentRollNumberExist(student)) {
            if (dm.delete(student)) {
                System.out.println("Student removed");
            }
        } else {
            System.out.println("Roll number " + student.rollNumber + " not found.");
        }
    }

    public void displayAllStudent() {
        System.out.println("R.N. Name                Grade");
        System.out.println("---- ------------------- -----");
        dm.selectAll();
    }

    public void searchStudent() {
        Student student = new Student();
        while (true) {
            try {
                System.out.println("\n1. Roll no.\n2. Name\n3. Grade");
                System.out.print("Search student by: ");
                Scanner sc = new Scanner(System.in);
                int choose = sc.nextInt();
                switch (choose) {
                    case 1 -> inputRollNumber(student);
                    case 2 -> inputName(student);
                    case 3 -> inputGrade(student);
                    default -> {
                        System.out.println(choose + " is invalid option, choose from 1,2 or 3");
                        continue;
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("choose from 1,2 or 3");
            }
        }
        System.out.println("R.N. Name                Grade");
        System.out.println("---- ------------------- -----");
        dm.searchPrintStudent(student);
    }

    private boolean isStudentRollNumberExist(Student student) {
        return dm.studentExist(student);
    }

    private void inputGrade(Student student) {
        while (true) {
            try {
                System.out.print("Grade: ");
                Scanner sc = new Scanner(System.in);
                student.grade = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("invalid grade!");
            }
        }
    }

    private void inputName(Student student) {
        while (true) {
            try {
                System.out.print("Name: ");
                Scanner sc = new Scanner(System.in);
                student.name = sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("invalid name!");
            }
        }
    }

    private void inputRollNumber(Student student) {
        while (true) {
            try {
                System.out.print("Roll no.: ");
                Scanner sc = new Scanner(System.in);
                student.rollNumber = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("invalid roll number!");
            }
        }
    }

}
