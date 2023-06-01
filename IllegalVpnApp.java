// --== CS400 P3W3 ==--
// Name: Pratham Patel
// CSL Username: PPatel
// Email: ppatel@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.util.Scanner;

public class IllegalVpnApp {

  public static void main(String[] args) {

    VpnFDBD backend = new VpnFDBD();
    // Use the frontend developer's code to drive the text-base user interface
    Scanner scanner = new Scanner(System.in);
    IllegalVpnFDInterface frontend = new IllegalVpnFD(backend, scanner);
    frontend.mainMenuPrompt();
  }


}

