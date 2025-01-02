package account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {

	public static void accountManagement() throws IOException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------Welcome to Account Management----------");
		ArrayList<Account> accountlist = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/bank_management";
		String DBuserName = "root";
		String DBpassword = "mohite@123";

		Connection connection = DriverManager.getConnection(url, DBuserName, DBpassword);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from Account");
		System.out.println(rs);

		while (true) {
			System.out.println("what would you like to do today!!!");
			System.out.println("1.Add account");
			System.out.println("2.Update account");
			System.out.println("3.Delete account");
			System.out.println("4.Search account");
			System.out.println("5.Display account");
			System.out.println("9.Quit");

			int option = sc.nextInt();
			switch (option) {
			case 1:
				Account account = new Account();
				System.out.println("\tEnter account number:");
				account.Account_no = sc.next();

				System.out.println("\tENter account type");
				account.Account_type = sc.next();

				System.out.println("\tEnter IFSC number:");
				account.Ifsc_number = sc.next();

				statement.execute("INSERT INTO Account(Account_no,Account_type, Ifsc_number) VALUES ('" + account.Account_no + "', '"
						+ account.Account_type + "', '" + account.Ifsc_number + "');");

				break;
			case 2:
				System.out.println("Update account");

				System.out.println("\tEnter the account number to Update:");
				String userAccountToUpdate = sc.next();

				System.out.println("\tEnter New accountNumber:");
				String newAccountNumber = sc.next();

				System.out.println("\tEnter New account type:");
				String newAccType = sc.next();

				System.out.println("\tEnter New ifsc code:");
				String newIfsc = sc.next();

				statement.execute("UPDATE Account SET Account_type = '" + newAccType + "', Ifsc_number = '" + newIfsc
						+ "' WHERE Account_no = '" + userAccountToUpdate + "';");

				break;
			case 3:
				System.out.println("Delete account:");
				System.out.println("Enter the account number to delete:");
				String accountToDelete = sc.next();
				statement.execute("DELETE FROM accounts WHERE accountNumber = '" + accountToDelete + "';");
				System.out.println("account Deleted Successfully.");

				break;
			case 4:
				System.out.println("Search account");
				System.out.println("Enter account number to Search");
				String AccountToSearch = sc.next();
		
				String searchQuery = "SELECT * FROM Account WHERE Account_no = '" + AccountToSearch + "';";
				ResultSet resultSet = statement.executeQuery(searchQuery);

				if (resultSet.next()) {
					System.out.println("Account Found Successfully");
					System.out.println("Account number:" + resultSet.getNString("Account_no"));
					System.out.println("Account type :" + resultSet.getNString("SAccount_type"));
					System.out.println("Ifsc number :" + resultSet.getNString("Ifsc_number"));

				} else {
					System.out.println("Account Not Found!");

				}

				break;
			case 5:
              System.out.println("Displaying account:");
				String printAccount = "SELECT * FROM Account;";
				ResultSet printSet = statement.executeQuery(printAccount);
				while (printSet.next()) {
					System.out.println("Account Number: " + printSet.getString("Account_no"));
					System.out.println("Account Type: " + printSet.getString("Account_type"));
					System.out.println("Ifsc code: " + printSet.getString("Ifsc_number"));
					System.out.println("-----------------------------------");
				}

				break;
			case 9:

				System.out.println("------Exit the Application------");
				return;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
}
