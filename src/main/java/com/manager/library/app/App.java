package com.manager.library.app;
import com.manager.library.entities.Book;
import com.manager.library.entities.Student;
import com.manager.library.frontendCLI.UserInterface;
import com.manager.library.service.LibraryService;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();
        UserInterface.home(scanner,libraryService);
        scanner.close();
        System.out.println("Program Closed!");

    }

}
