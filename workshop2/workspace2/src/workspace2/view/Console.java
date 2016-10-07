package workspace2.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements View {

	public String getInput() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String text = null;
		try {
			text = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return text;
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void changeInfoMenu() {
		System.out.println("#######################");
		System.out.println("1. Name");
		System.out.println("2. Personal number");
		System.out.println("3. Boat information");
		System.out.println("#######################");
	}

	public void changeBoatMenu() {
		System.out.println("#######################");
		System.out.println("1. Type");
		System.out.println("2. Length");
		System.out.println("3. Both");
		System.out.println("#######################");
	}

	public void listMenu() {
		System.out.println("#######################");
		System.out.println("1. Compact List");
		System.out.println("2. Verbose List");
		System.out.println("#######################");
	}

	public void listBoatType() {
		System.out.println("#######################");
		System.out.println("1. Sailboat");
		System.out.println("2. Motorsailer");
		System.out.println("3. kayak/Canoe");
		System.out.println("4. Other");
		System.out.println("#######################");

	}

	@Override
	public void welcomeMenu() {
		System.out.println("Press a number to perform an action or press q to quit");
		System.out.println("#######################");
		System.out.println("1. Add a member");
		System.out.println("2. Delete member");
		System.out.println("3. Change member information");
		System.out.println("4. List all members");
		System.out.println("5. Register a boat");
		System.out.println("6. Delete boat");
		System.out.println("#######################");
	}

}
