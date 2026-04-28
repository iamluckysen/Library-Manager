package com.manager.library.app;
import com.manager.library.frontendCLI.UserInterface;
import com.manager.library.service.LibrayServiceCLI;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        LibrayServiceCLI libraryService = new LibrayServiceCLI();
        UserInterface.home(scanner, libraryService);
        scanner.close();
        System.out.println("Program Closed!");


    }

}
