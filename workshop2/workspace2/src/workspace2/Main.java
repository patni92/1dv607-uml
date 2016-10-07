package workspace2;

import workspace2.controller.User;
import workspace2.model.BoatClub;
import workspace2.view.Console;
import workspace2.view.View;

public class Main {
	 public static void main(String[] args) {
	        BoatClub boatClub = new BoatClub();
	        View view = new Console();
	        User user = new User(view, boatClub);
	        
	        user.useSystem();
	}
}
