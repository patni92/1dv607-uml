package workspace2.view;

public interface View {
	void welcomeMenu();

	String getInput();

	public void displayMessage(String message);

	public void listMenu();

	public void listBoatType();

	public void changeInfoMenu();

	void changeBoatMenu();
}
