package workspace2.model;

public class Boat {
	private String type;
	private String length;

	public Boat(String type, String length) {
		this.type = type;
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
