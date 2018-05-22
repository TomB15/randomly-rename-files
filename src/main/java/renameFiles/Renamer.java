package main.java.renameFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Renamer {

	private ArrayList<Integer> numbers = new ArrayList<>();
	private Window window;
	
	public Renamer(Window window){
		this.window = Objects.requireNonNull(window);
	}

	public void rename(String directoryPath) {
		final File directoryValues = new File(directoryPath);

		try {
			findFiles(directoryValues, directoryPath);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void findFiles(final File directoryValues, String directoryPath) {
		for (final File fileEntry : directoryValues.listFiles()) {
			if (fileEntry.isDirectory()) {
				findFiles(fileEntry, directoryPath);
			} else {
				changeFileName(fileEntry, directoryPath);
			}
		}
	}
	
	private void changeFileName(File file, String directoryPath){
		String oldName = file.getName();
		Path source = Paths.get(directoryPath + "/" + oldName);

		String fileExtension = retrieveExtension(oldName);

		String newName = String.valueOf(generateRandomNumber() + fileExtension);

		try {
			Files.move(source, source.resolveSibling(newName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		printName(file, newName);
	}
	
	private String retrieveExtension(String oldName){
		String fileExtension = "";
		int i = oldName.lastIndexOf(".");
		if (i > 0) {
			fileExtension = oldName.substring(i);
		}
		
		return fileExtension;
	}

	private int generateRandomNumber() {

		int random = 0;
		boolean repeat = true;

		while (repeat) {
			random = ThreadLocalRandom.current().nextInt(100000, 999999);
			if (!numbers.contains(random))
				repeat = false;
		}

		return random;
	}
	
	private void printName(File file, String newName){
		String printText = file.getName() + " = " + newName;
		System.out.println(printText);
		window.printText(printText + "\n");
	}
}
