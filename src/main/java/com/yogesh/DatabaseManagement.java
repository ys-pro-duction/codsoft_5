package com.yogesh;

import java.sql.*;

public class DatabaseManagement {

    Connection db;

    public DatabaseManagement() {
        connect();
    }

    private void connect() {
        var url = "jdbc:sqlite:students.db";
        var sql = "CREATE TABLE IF NOT EXISTS student(roll_num INTEGER PRIMARY KEY, name text NOT NULL, grade INTEGER NOT NULL);";
        try {
            db = DriverManager.getConnection(url);
            db.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(Student student) {
        try {
            System.out.println();
            PreparedStatement statement = db.prepareStatement("INSERT INTO student(roll_num,name,grade) VALUES(?,?,?)");
            statement.setInt(1, student.rollNumber);
            statement.setString(2, student.name);
            statement.setInt(3, student.grade);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to add student, Reason: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Student student) {
        try {
            PreparedStatement statement = db.prepareStatement("UPDATE student set name = ?, grade = ? WHERE roll_num = ?");
            statement.setString(1, student.name);
            statement.setInt(2, student.grade);
            statement.setInt(3, student.rollNumber);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to update student, Reason: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(Student student) {
        try {
            PreparedStatement statement = db.prepareStatement("DELETE FROM student WHERE roll_num = ?");
            statement.setInt(1, student.rollNumber);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to remove student, Reason: " + e.getMessage());
        }
        return false;
    }

    public void selectAll() {
        try {
            ResultSet result = db.createStatement().executeQuery("SELECT * FROM student");
            while (result.next()) {
                System.out.printf("%-5s%-20s%-5s%n", result.getInt(1), result.getString(2), result.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Unable to query student, Reason: " + e.getMessage());
        }
    }

    public void searchPrintStudent(Student student) {
        try {
            String searchBy = "";
            if (student.rollNumber != Integer.MIN_VALUE) searchBy = "roll_num = " + student.rollNumber;
            else if (student.name != null) searchBy = "name = \"" + student.name+"\"";
            else if (student.grade != Integer.MIN_VALUE) searchBy = "grade = " + student.grade;
            ResultSet result = db.createStatement().executeQuery("SELECT * FROM student WHERE " + searchBy);
            while (result.next()) {
                System.out.printf("%-5s%-20s%-5s%n", result.getInt(1), result.getString(2), result.getInt(3));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Unable to search student, Reason: " + e.getMessage());
        }
    }

    public boolean studentExist(Student student) {
        try {
            ResultSet found = db.createStatement().executeQuery("SELECT roll_num, name, grade FROM student where roll_num = " + student.rollNumber + ";");
            return student.rollNumber == found.getInt(1);
        } catch (SQLException e) {
        }
        return false;
    }
}
