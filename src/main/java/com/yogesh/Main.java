package com.yogesh;

import java.util.Scanner;

public class Main {

    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        System.out.println("=========================");
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("=========================");
        while (true){
            try {
                System.out.println();
                System.out.println("1. Display all students\n2. Find student\n" +
                        "3. Add new student\n4. Update existing student\n" +
                        "5. Remove student\n6. Exit");
                System.out.print("Choose action: ");
                Scanner input = new Scanner(System.in);
                int action = input.nextInt();
                switch (action){
                    case 1 -> sms.displayAllStudent();
                    case 2 -> sms.searchStudent();
                    case 3 -> sms.addStudent();
                    case 4 -> sms.updateStudent();
                    case 5 -> sms.removeStudent();
                    case 6 -> {
                        return;
                    }
                    default -> throw new Exception("invalid action");
                }
            }catch (Exception e){
                System.out.println("Choose valid action!");
            }
        }
    }
}
class Student {
    int rollNumber = Integer.MIN_VALUE;
    String name;
    int grade = Integer.MIN_VALUE;
}

