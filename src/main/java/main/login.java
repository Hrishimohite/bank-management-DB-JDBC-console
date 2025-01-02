package main;

import java.util.Scanner;

public class login {
	public static void login() {
		System.out.println("Enter user name:");
		Scanner sc=new Scanner(System.in);
		String UserName = sc.next();
		System.out.println("Enter password:");
		String Password=sc.next();
		
		if (UserName.equals("hsm")) {
			if (Password.equals("123")) {
				System.out.println("loogin successfully");
				return;
			}
		}else {
			
			System.out.println("Invalid login !");
			System.exit(0);
		}
	}
}
