package workspace2.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Member {
	private int memberId;
	private String name;
	private int personalNumber;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();
	private int numberOfBoats = boatList.size();

	public Member (String name, int personalNumber,int memberId) {
		this.name = name;
		this.personalNumber = personalNumber;
		this.memberId = memberId;
	}
	

	public int getNumberOfBoats() {
		return numberOfBoats;
	} 
	
	public JSONObject getJsonObject() {
		JSONArray boatsArray = new JSONArray();
		JSONObject memberObj = new JSONObject();
		
		if(boatList.size() > 0) {
			for(int i = 0; i < boatList.size(); i+= 1) {
				JSONObject boatObj = new JSONObject();
				boatObj.put("type", boatList.get(i).getType());
				boatObj.put("length", boatList.get(i).getLength());
				boatsArray.add(boatObj);
				
		}
		}
		
		
		

		memberObj.put("name", name);
		memberObj.put("personalNumber", personalNumber);
		memberObj.put("memberId", memberId);
		memberObj.put("boats", boatsArray);
		
		
		return memberObj;
	}
	
	public Boat findBoat(int index) {
		if(boatList.size() > 0) {
			for (int i = 0; i < boatList.size(); i += 1) {
				if(index == i && boatList.get(i) != null);
				return boatList.get(i);
			}
		}
		
		return null;
	}
	
	public String verboseToString () {
		String boats = "";
		
		for(int i = 0; i < boatList.size(); i+=1) {
			boats += " boat " + (i + 1) + " (Type: " + boatList.get(i).getType() + " Length: " + boatList.get(i).getLength() + "),";
		}
		return "name: " + name +  "\nmemberId: " + memberId + "\npersonal number:"+ personalNumber  + "\nBoats: " + boats +"\n";
	}
	
	public String compactToString () {
		return "name: " + name +  "\nmemberId: " + memberId + "\nNumber of boats: " +  boatList.size() + "\n";
	} 
	
	public String boatsToString() {
		String boatString = "";
		for(int i = 0; i < boatList.size(); i+=1) {
			boatString += " boat " + (i + 1) + " (Type: " + boatList.get(i).getType() + " Length: " + boatList.get(i).getLength() + "),";
		}
		
		return boatString;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public void setPNumber(int number) {
		this.personalNumber = number;
	}
	
	public int getPNumber() {
		return this.personalNumber;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	
	public void addBoat(String type, String length) {
		boatList.add(new Boat(type, length));
		numberOfBoats += 1;
	}
	
	public void removeBoat(int index) {
		boatList.remove(index);
		numberOfBoats -= 1;
	}
	
}
