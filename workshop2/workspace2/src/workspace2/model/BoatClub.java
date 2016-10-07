package workspace2.model;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BoatClub {

	private ArrayList<Member> members = new ArrayList<Member>();
	private int idCount = 1;

	public void addMember(String name, int personalNumber, boolean save, Integer... mId) {

		int memberId = 0;

		if (mId.length > 0) {
			memberId = mId[0];
		} else {
			memberId = idCount;
		}

		Member member = new Member(name, personalNumber, memberId);
		members.add(member);
		idCount += 1;
		try {
			if (save) {
				saveData();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Member findMember(int id) {
		Member member;
		for (int i = 0; i < members.size(); i += 1) {
			if (id == members.get(i).getMemberId()) {
				return member = members.get(i);
			}

		}

		return null;
	}

	public String deleteMember(int id) {
		Member member = findMember(id);

		if (member != null) {
			members.remove(member);

			try {
				saveData();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {

				e.printStackTrace();
			}

			return member.getName();
		}

		return "0 members";
	}

	public Iterator listMembers() {
		return members.iterator();
	}

	public void readData() throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("resources/sample.json");
		Object obj = parser.parse(reader);
		JSONArray list = (JSONArray) obj;

		JSONObject readObj = new JSONObject();
		for (int i = 0; i < list.size(); i += 1) {
			readObj = (JSONObject) list.get(i);
			JSONArray jsonBoats = (JSONArray) readObj.get("boats");

			String name = (String) readObj.get("name");
			int personalNumber = Integer.parseInt(readObj.get("personalNumber").toString());
			Integer memberId = Integer.parseInt(readObj.get("memberId").toString());

			addMember(name, personalNumber, false, memberId);

			if (jsonBoats.size() > 0) {
				for (int z = 0; z < jsonBoats.size(); z += 1) {
					JSONObject jsonBoat = (JSONObject) jsonBoats.get(z);
					String boatType = jsonBoat.get("length").toString();
					String length = jsonBoat.get("type").toString();
					members.get(i).addBoat(boatType, length);
				}
			}

		}
		reader.close();

		idCount = (Integer.parseInt(readObj.get("memberId").toString()) + 1);

	}

	public void saveData() throws IOException, ParseException {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < members.size(); i += 1) {
			JSONObject memberObj = members.get(i).getJsonObject();

			jsonArray.add(memberObj);
		}

		FileWriter fileWriter = new FileWriter("resources/sample.json");
		fileWriter.write(jsonArray.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	}
}
