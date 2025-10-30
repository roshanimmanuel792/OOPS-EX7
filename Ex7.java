package exp7;

import java.util.ArrayList;
import java.util.Scanner;

// Custom exception for invalid empid
class InvalidEmpidException extends Exception {
    public InvalidEmpidException(String message) {
        super(message);
    }
}

// Custom exception for invalid name
class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

// Custom exception for invalid age
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Employee class
class Employee {
    int empid;
    String name;
    int age;

    public Employee(int empid, String name, int age) {
        this.empid = empid;
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("\nEmployee Details:");
        System.out.println("Employee ID: " + empid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Main class
public class exp7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n=== Employee Management Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int empid = sc.nextInt();
                        sc.nextLine(); // consume newline

                        if (String.valueOf(empid).length() < 4) {
                            throw new InvalidEmpidException("Employee ID must be at least 4 digits!");
                        }

                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        if (name.matches("[0-9]+")) {
                            throw new InvalidNameException("Name cannot be a number!");
                        }

                        System.out.print("Enter Employee Age: ");
                        int age = sc.nextInt();

                        if (age > 50) {
                            throw new InvalidAgeException("Age cannot be greater than 50!");
                        }

                        Employee emp = new Employee(empid, name, age);
                        employees.add(emp);
                        System.out.println("Employee added successfully!");

                    } catch (InvalidEmpidException | InvalidNameException | InvalidAgeException e) {
                        System.out.println("Error: " + e.getMessage());
                        sc.nextLine(); // clear buffer in case of invalid input
                    } catch (Exception e) {
                        System.out.println("Unexpected Error: " + e.getMessage());
                        sc.nextLine(); // clear buffer
                    }
                    break;

                case 2:
                    if (employees.isEmpty()) {
                        System.out.println("No employees to display.");
                    } else {
                        for (Employee emp : employees) {
                            emp.display();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }

        } while (choice != 3);

        sc.close();
    }
}
