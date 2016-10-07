package workspace2.controller;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.parser.ParseException;


import workspace2.model.BoatClub;
import workspace2.model.Member;
import workspace2.view.View;

public class User {
	
	private View view;
	private BoatClub boatClub;
	
	private String newName() {
		view.displayMessage("Enter new name");
		String name = view.getInput();
		
		while(isNumeric(name)) {
			view.displayMessage("Enter new name");
			name = view.getInput();
		}
		
		return name;
	}
	
	private int validateInt(String message) {
		String input;
		int id;
		do {
			view.displayMessage(message);
			input =view.getInput();
		}
			
			while(! isNumeric(input));
		return Integer.parseInt(input);
	}
	
	
	public User(View view, BoatClub boatClub) {
		this.view = view;
		this.boatClub = boatClub;
	}
	
	private void addMember() {
		String name = newName();
		int pNumber = validateInt("Enter personal number");
		
		
		boatClub.addMember(name, pNumber, true);
		
		
	}
	
	private void deleteMember() {
		int id = validateInt("Enter id of member to remove");
	
		
			
			String deletedMember = boatClub.deleteMember(id);
			view.displayMessage("removed "+ deletedMember);
	}
	
	private boolean changeInfo() {
		
		int memberId = validateInt("Enter member id");
		
		
		
		Member member = boatClub.findMember(memberId);
		int menuNumber; 
		if(member == null) {
			return false;
		}
		
		
		do {
			view.changeInfoMenu();
			String menuOption = view.getInput();
			if(!isNumeric(menuOption )) {
				menuOption  = "0";
			}
			menuNumber = Integer.parseInt(menuOption);
			}while(menuNumber != 1 && menuNumber != 2  && menuNumber != 3);
		
			if(menuNumber == 1) {
				String name = newName();
				member.setName(name);
				
			} else if(menuNumber == 2) {
				
				int pn = validateInt("Enter new personal number");
				
				
				
				member.setPNumber(pn);
			} else if(menuNumber == 3) {
				
				int boatNumber;
				
				do {
					view.displayMessage("pick number for boat");
					view.displayMessage(member.boatsToString());
					String menuOption = view.getInput();
					if(!isNumeric(menuOption )) {
						menuOption  = "0";
					}
					menuNumber = Integer.parseInt(menuOption);
					boatNumber = menuNumber;
					}while(menuNumber > member.getNumberOfBoats() || menuNumber <= 0 );
				
				
			
				do {
					view.changeBoatMenu();
					String menuOption = view.getInput();
					if(!isNumeric(menuOption )) {
						menuOption  = "0";
					}
					menuNumber = Integer.parseInt(menuOption);
					}while(menuNumber != 1 && menuNumber != 2  && menuNumber != 3);
				if(menuNumber == 1) {
					
					String type = null;
		
					String menuOption;
					view.displayMessage("Set Type");
					
					int menuNr;
					do {
						view.listBoatType();
						menuOption = view.getInput();
						if(!isNumeric(menuOption )) {
							menuOption  = "0";
						}
						menuNumber = Integer.parseInt(menuOption);
						}while(menuNumber != 1 && menuNumber != 2  && menuNumber != 3 && menuNumber != 4);
					
					menuNr = Integer.parseInt(menuOption);
					if(menuNr == 1) {
						type = "Sailboat";
						
					} else if (menuNr == 2) {
						type = "Motorsailer";
					} else if (menuNr == 3) {
						type = "kayak/Canoe";
					} else if (menuNr == 4) {
						type = "Others";
					}
					
					member.findBoat(boatNumber).setType(type);
					
					
				} else if(menuNumber == 2) {
					view.displayMessage("Enter length");
					String length = view.getInput();
					member.findBoat(boatNumber).setLength(length);;
				}
			}
			
			try {
				boatClub.saveData();
				view.displayMessage("Information updated to");
				view.displayMessage(member.verboseToString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		
	}
	
	private void listMembers() {
		String inputMenu;
		int menuNumber;
		do {
			view.listMenu();
			inputMenu = view.getInput();
			if(!isNumeric(inputMenu)) {
				inputMenu = "0";
			}
			menuNumber = Integer.parseInt(inputMenu);
			}while(menuNumber != 1 && menuNumber != 2);
		
		Iterator list = boatClub.listMembers();
		
		if(menuNumber == 1) {
			while(list.hasNext()) {
				Member member = (Member) list.next();
				view.displayMessage(member.compactToString());
				
			}
		} else {
			while(list.hasNext()) {
				Member member = (Member) list.next();
				view.displayMessage(member.verboseToString());
				
			}
		}
		
		
	}
	
	private void addBoat () {
		view.displayMessage("Enter member id");
		int memberId = Integer.parseInt(view.getInput());
		Member member = boatClub.findMember(memberId);
		
		if(member != null) {
			String boatType = null;
			view.listBoatType();
			
			int menuNr = Integer.parseInt(view.getInput());
			if(menuNr == 1) {
				boatType = "Sailboat";
			} else if (menuNr == 2) {
				boatType = "Motorsailer";
			} else if (menuNr == 3) {
				boatType = "kayak/Canoe";
			} else if (menuNr == 2) {
				boatType = "Others";
			}
			
			
			
			view.displayMessage("How long is the boat?");
			String length = view.getInput();
			
			
			
		
			member.addBoat(boatType, length);
		}
		
		
	}
	
	private void deleteBoat() {
		int memberId = validateInt("Enter id of member");
		 Member member = boatClub.findMember(memberId);
		int boatNumber;
		int menuNumber;
		if(member != null) {
			do {
				view.displayMessage("Enter number of the boat to delete");
				view.displayMessage(member.boatsToString());
				String menuOption = view.getInput();
				if(!isNumeric(menuOption )) {
					menuOption  = "0";
				}
				menuNumber = Integer.parseInt(menuOption);
				boatNumber = menuNumber;
				}while(menuNumber > member.getNumberOfBoats() || menuNumber <= 0 );
			
			member.removeBoat(boatNumber - 1);
			
			try {
				boatClub.saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

	public void useSystem() {
		try {
			boatClub.readData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		view.welcomeMenu();
		String inputChar = view.getInput();
		
		
		
		while (! inputChar.equals("q")) {
			
			if(!isNumeric(inputChar)) {
				inputChar = "0";
			}
			switch (Integer.parseInt(inputChar)) {
			case 1: 
					addMember();
				break;
			case 2: 
				
				deleteMember();
				break;
				
			case 3:
				changeInfo();
				break;
				
			case 4: 
					listMembers();
					
				break;
				
			case 5:
				
				addBoat();
				
				break;
			
			case 6:
				deleteBoat();
				break;
			}
			view.welcomeMenu();
			inputChar = view.getInput();
			

		}
	}

	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
