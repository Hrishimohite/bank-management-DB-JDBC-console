package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
	public static void userManagement() throws IOException, SQLException {

		System.out.println("----------Welcome to UserMAnagement-------");
		String url = "jdbc:mysql://localhost:3306/bank_management";
		String DBuserName = "root";
		String DBpassword = "mohite@123";

		Connection connection = DriverManager.getConnection(url, DBuserName, DBpassword);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from users");
		System.out.println(rs);

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("What would you like to do today?");
			System.out.println("1.Add User");
			System.out.println("2.Update User");
			System.out.println("3.Delete User");
			System.out.println("4.Search User");
			System.out.println("5.Print User");
			System.out.println("9.Quit ");

			int option = sc.nextInt();
			switch (option) {
			case 1:
				User user = new User();

				System.out.println("\tEnter User Name:");
				user.UserName = sc.next();

				System.out.println("\tEnter User Email:");
				user.Email = sc.next();

				System.out.println("\tEnter User Phonenumber");
				user.PhoneNumber = sc.next();

				statement.execute("INSERT INTO users(username, email, PhoneNumber) VALUES ('" + user.UserName + "', '"
						+ user.Email + "', '" + user.PhoneNumber + "');");

				break;
			case 2:

				System.out.println("Update User");

				System.out.println("\tEnter the User Name to Update:");
				String userNameToUpdate = sc.next();

				System.out.println("\tEnter New Email:");
				String newEmail = sc.next();

				System.out.println("\tEnter New Phone Number:");
				String newPhoneNumber = sc.next();

				statement.execute("UPDATE users SET email = '" + newEmail + "', PhoneNumber = '" + newPhoneNumber
						+ "' WHERE username = '" + userNameToUpdate + "';");

				// UPDATE- it is SQL command used to modify existing data
				// SET: This keyword specifies the columns to update and their new values.
				// WHERE- specifies which row should be updated

				break;
			case 3:
				System.out.println("Delete User ");
				System.out.println("Enter User name to Delete:");
				String userToDelete = sc.next();
				statement.execute("DELETE FROM users WHERE username = '" + userToDelete + "';");
				System.out.println("User deleted successfully.");

				break;
			case 4:
				System.out.println("Search User");
				System.out.println("Enter User name to Search:");
				String userToSearch = sc.next();
				String searchQuery = "SELECT * FROM users WHERE username = '" + userToSearch + "';";
				ResultSet resultSet = statement.executeQuery(searchQuery);

				if (resultSet.next()) {
					System.out.println("User found:");
					System.out.println("Username: " + resultSet.getString("username"));
					System.out.println("Email: " + resultSet.getString("email"));
					System.out.println("Phone Number: " + resultSet.getString("PhoneNumber"));
				} else {
					System.out.println("User not found.");
				}

				break;
			case 5:
				System.out.println("Print User ");

				String printUser = "SELECT * FROM users;";
				ResultSet printSet = statement.executeQuery(printUser);
				while (printSet.next()) {
					System.out.println("Username: " + printSet.getString("username"));
					System.out.println("Email: " + printSet.getString("email"));
					System.out.println("PhoneNumber: " + printSet.getString("PhoneNumber"));
					System.out.println("-----------------------------------");
				}

				break;
			case 9:
				System.out.println("quit");
				return;
			default:
				System.out.println("INvalid Option Selected");
			}

		}
	}

}
