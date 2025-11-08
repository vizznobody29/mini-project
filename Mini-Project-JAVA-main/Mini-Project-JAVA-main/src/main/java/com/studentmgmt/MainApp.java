package com.studentmgmt;

import com.studentmgmt.model.Student;
import com.studentmgmt.service.FeeService;
import com.studentmgmt.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // 1. Load the Spring Configuration
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Get the Service beans from the Spring context
        StudentService studentService = context.getBean(StudentService.class);
        FeeService feeService = context.getBean(FeeService.class);

        // --- Pre-populate some data ---
        studentService.addCourse("Java Full Stack", 6);
        studentService.addCourse("Python Data Science", 5);
        studentService.addStudent("Alice Smith");
        studentService.addStudent("Bob Johnson");
        studentService.assignCourseToStudent(1, 1); // Assign Java to Alice
        // ------------------------------
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Online Student Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View Student Details");
            System.out.println("3. View All Students");
            System.out.println("4. Make Fee Payment");
            System.out.println("5. Issue Fee Refund");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        studentService.addStudent(name);
                        System.out.println("Student added!");
                        break;
                    case 2:
                        System.out.print("Enter student ID: ");
                        int viewId = scanner.nextInt();
                        Student student = studentService.getStudentById(viewId);
                        System.out.println(student);
                        break;
                    case 3:
                        List<Student> students = studentService.getAllStudents();
                        students.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.print("Enter student ID for payment: ");
                        int payId = scanner.nextInt();
                        System.out.print("Enter payment amount: ");
                        double payAmount = scanner.nextDouble();
                        feeService.makePayment(payId, payAmount);
                        break;
                    case 5:
                        System.out.print("Enter student ID for refund: ");
                        int refundId = scanner.nextInt();
                        System.out.print("Enter refund amount: ");
                        double refundAmount = scanner.nextDouble();
                        feeService.issueRefund(refundId, refundAmount);
                        break;
                    case 6:
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = scanner.nextInt();
                        studentService.deleteStudent(deleteId);
                        System.out.println("Student deleted.");
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        context.close(); // Close the Spring context
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}