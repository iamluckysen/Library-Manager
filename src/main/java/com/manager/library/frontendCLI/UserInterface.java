package com.manager.library.frontendCLI;

import com.manager.library.entities.Student;
import com.manager.library.service.LibrayServiceCLI;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
        public static void home(Scanner input, LibrayServiceCLI libraryService) {
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
        public static void studentLogin(Scanner input,LibrayServiceCLI libraryService) {
            System.out.println("Logging in as Student");
            String prompt = "Enter Student ID : ";
            int stdID = getSafeInteger(input,prompt);
            Student student = libraryService.getStudentById(stdID);
              if (student == null) {
                  System.out.println("Student not found !");
                  System.out.println("Try Again!");
              } else {
                  System.out.println("*********Logged In Successfully*********");
                  System.out.println("Welcome : " + student.getName());
                  boolean isLoggedIn = true;
                  while (isLoggedIn) {
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
                              String borrow_prompt = "Enter Book Id : ";
                              int bookID_b = getSafeInteger(input,borrow_prompt);
                              libraryService.borrowBook(bookID_b, stdID);
                              break;
                          case "R":
                              String return_prompt = "Enter Book Id : ";
                              int bookID_r = getSafeInteger(input,return_prompt);
                              libraryService.returnBook(bookID_r, stdID);
                              break;
                          case "S":
                              String search_prompt = "Enter Book Id : ";
                              int bookID_s = getSafeInteger(input,search_prompt);
                              libraryService.getBookById(bookID_s);
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
        }
        public static void studentRegistration(Scanner input, LibrayServiceCLI libraryService) {
        System.out.println("******REGISTERING A STUDENT*********");
        String name = getSafeString(input, "Enter Your Full Name : ");
        String email = getUniqueEmail(input, libraryService,"Enter Email Address : ");
        String phone = getSafeString(input, "Enter Phone Number : ");

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setObtainedBook(0);
        student.setPhone(phone);

        try {
            libraryService.addNewStudent(student);
            System.out.println("Student Registered Successfully!");
            System.out.println("******Your Details******");
            System.out.println(student);
            System.out.println("************************");
            System.out.println("Keep Your Student id for future login!");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println("Try Again!");
        }
    }
        public static int getSafeInteger(Scanner input , String message) {
            while(true) {
                System.out.print(message);
                try {
                   int value = input.nextInt();
                   input.nextLine();
                   return value;
                }catch (InputMismatchException e) {
                    System.out.println("Input Mismatch!");
                    System.out.println("try again!");
                    input.nextLine();
                }
            }
        }
        public static String getSafeString(Scanner input , String message) {
            String value = "";
            while (value.trim().isEmpty()) {
                System.out.print(message);
                value = input.nextLine();
                if (value.trim().isEmpty()) {
                    System.out.println("This field cannot be empty!");
                    System.out.println("try again!");
                }
            }
            return value;
        }
        public static String getUniqueEmail(Scanner input,LibrayServiceCLI service, String message){
            while(true){
                String email = getSafeString(input, message);
                if(service.emailExists(email)){
                    System.out.println("Email Already in use");
                    System.out.println("Provide different email");
                }else{
                    return email;
                }
            }
        }
}
