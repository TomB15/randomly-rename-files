package main.java.renameFiles;

public class App {

	public static void main(String args[]){
		Window window = new Window();		
		Renamer renamer = new Renamer(window);
		window.setActionBtnListener(renamer); //todo dát actionliestener do window?
    }
}
