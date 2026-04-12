package com.manager.library.frontendCLI;

import com.manager.library.entities.Student;
import com.manager.library.service.LibraryService;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {



        public static void home(Scanner input,LibraryService libraryService) {
            boolean exit = false;
            System.out.println("=====================================");
            System.out.println("  Welcome to the Library Manager!    ");
            System.out.println("=====================================");
            System.out.println();
            while(!exit) {
                System.out.println("Press L : To Login As Student");
                System.out.println("Press R : To Register As Student");
                System.out.println("Press Q : To Quit");
                System.out.println("===================================");
                System.out.println();
                System.out.print("Enter your choice : ");
                String resp = input.nextLine().toUpperCase(Locale.ENGLISH).trim();
                switch (resp) {
                    case "L":
                        studentLogin(input,libraryService);
                        break;
                    case "R":
                        studentRegistration(input, libraryService);
                        break;
                    case "Q":
                        exit=true;
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }
        }
        public static void studentLogin(Scanner input,LibraryService libraryService) {
            System.out.println("Logging in as Student");
            System.out.print("Enter Student Id :  ");
            int stdID = input.nextInt();
            input.nextLine();
            Student student = libraryService.getStudentById(stdID);
            if(student == null) {
                System.out.println("Student not found !");
                System.out.println("Incorrect Student Id !");
            }
            System.out.println("*********Logged In Successfully*********");
            System.out.println("Welcome : "+ student.getName() );
            boolean isLoggedIn = true;
            while(isLoggedIn) {
                System.out.println("Press A : To See All Books");
                System.out.println("Press B : To Borrow Books");
                System.out.println("Press R : To Return Books");
                System.out.println("Press M : To See Your Books");
                System.out.println("Press S : Search Book By Id");
                System.out.println("Press O : Log Out");
                System.out.print("Enter your choice : ");
                String resp = input.nextLine().toUpperCase(Locale.ENGLISH).trim();
                switch (resp) {
                    case "A":
                        libraryService.getBooklist();
                        break;
                    case "B":
                        System.out.print("Enter Book Id : ");
                        int bookID = input.nextInt();
                        input.nextLine();
                        libraryService.borrowBook(bookID, stdID);
                        break;
                    case "R":
                        System.out.print("Enter Book Id : ");
                        int bookID2 = input.nextInt();
                        input.nextLine();
                        libraryService.returnBook(bookID2, stdID);
                        break;
                    case "S":
                        System.out.print("Enter Book Id : ");
                        int bookID3 = input.nextInt();
                        input.nextLine();
                        libraryService.getBookById(bookID3);
                        break;
                    case "M":
                       libraryService.getMyBookList(stdID);
                       break;
                    case "O":
                        isLoggedIn = false;
                        System.out.println("Logged Out Successfully");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }

        }
        public static void studentRegistration(Scanner input,LibraryService libraryService) {
            System.out.println("******REGISTERING A STUDENT*********");
            System.out.print("Enter Your  Full Name : ");
            String name = input.nextLine();
            System.out.println("Enter Your Email : ");
            String email = input.nextLine();
            System.out.println("Enter Your Phone : ");
            String phone = input.nextLine();
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setObtainedBook(0);
            student.setPhone(phone);
            libraryService.addNewStudent(student);
            if(libraryService.getStudentById(student.getId()) != null) {
                System.out.println("Student Registered Successfully!");
                System.out.println("******Your Details******");
                System.out.println(student);
                System.out.println("************************");
                System.out.println("Keep Your Student id for future login!");
                return;
            }
            System.out.println("Something went wrong!");
            System.out.println("Try Again!");



        }


}
