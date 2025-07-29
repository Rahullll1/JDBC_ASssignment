package com.jdbc.main;

import java.util.*;
import java.sql.SQLException;
import com.jdbc.model.Department;
import com.jdbc.service.DbService;

public class MainClass {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DbService service = new DbService();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Department Management System ===");
            System.out.println("1. Add Department");
            System.out.println("2. View Department by ID");
            System.out.println("3. View All Departments");
            System.out.println("4. Update Department");
            System.out.println("5. Delete Department");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        addDepartment();
                        break;
                    case 2:
                        getDepartmentById();
                        break;
                    case 3:
                        getAllDepartments();
                        break;
                    case 4:
                        updateDepartment();
                        break;
                    case 5:
                        deleteDepartment();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 6);
    }

    private static void addDepartment() throws SQLException {
        System.out.print("Enter Dept ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Dept Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Location: ");
        String loc = scanner.nextLine();
        Department dept = new Department(id, name, loc);
        service.addDepartment(dept);
        System.out.println("Department added.");
    }

    private static void getDepartmentById() throws SQLException {
        System.out.print("Enter Dept ID: ");
        int id = scanner.nextInt();
        Department dept = service.getDepartmentById(id);
        if (dept != null) {
            System.out.println(dept);
        } else {
            System.out.println("Department not found.");
        }
    }

    private static void getAllDepartments() throws SQLException {
        List<Department> list = service.getAllDepartments();
        for (Department d : list) {
            System.out.println(d);
        }
    }

    private static void updateDepartment() throws SQLException {
        System.out.print("Enter Dept ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Location: ");
        String loc = scanner.nextLine();
        Department dept = new Department(id, name, loc);
        if (service.updateDepartment(dept)) {
            System.out.println("Department updated.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private static void deleteDepartment() throws SQLException {
        System.out.print("Enter Dept ID to delete: ");
        int id = scanner.nextInt();
        if (service.deleteDepartment(id)) {
            System.out.println("Department deleted.");
        } else {
            System.out.println("Deletion failed.");
        }
    }
}
