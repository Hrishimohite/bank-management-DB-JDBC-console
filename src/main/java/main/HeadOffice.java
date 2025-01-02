package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import account.AccountManagement;
import user.UserManagement;

public class HeadOffice {
	public static void main(String[] args) throws SQLException,IOException  {
		Scanner Sc = new Scanner(System.in);
		System.out.println("---------Welcome to Bank Management $---------- ");
		login.login();
		String url = "jdbc:mysql://localhost:3306/bank_management";
		String userName = "root";
		String password = "mohite@123";

		Connection connection = DriverManager.getConnection(url, userName, password); // create connection
		Statement statement = connection.createStatement(); // SQL Script editor
		ResultSet result = statement.executeQuery("select * from users");
		while (true) { // game loop
			System.out.println("What would you like to do today");
			System.out.println("1.Acount Management");
			System.out.println("2.User Management");
			System.out.println("3.Loan Management");
			System.out.println("4.Quit");

			int input = Sc.nextInt();

			switch (input) {
			case 1:
				AccountManagement.accountManagement();
				break;
			case 2:
				UserManagement.userManagement();
				break;

			case 4:
				System.out.println("Exit Application");
				return;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}
