package com.changeFileNames;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * tutorial https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
 */
public class Rename {

    private ArrayList<Integer> numbers = new ArrayList<>();
    private String path = "";

    private Window window;

    public void rename(String path, Window window){

        this.path = path;
        this.window = window;

        final File folder = new File(path);

        try{
            listFileForFolder(folder);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    private void listFileForFolder(final File folder){
        for (final File fileEntry: folder.listFiles()){
            if(fileEntry.isDirectory()){
                listFileForFolder(fileEntry);
            }else{
                String oldName = fileEntry.getName();
                Path source = Paths.get(path + "\\" + oldName);

                String fileExtension = "";
                int i = oldName.lastIndexOf(".");
                if(i > 0){
                    fileExtension = oldName.substring(i);
                }

                String newName = String.valueOf(randomNumber() + fileExtension);

                try{
                    Files.move(source, source.resolveSibling(newName));
                }catch(IOException e){
                    e.printStackTrace();
                }

                String printText = fileEntry.getName() + " = " + newName;
                System.out.println(printText);
                window.printText(printText + "\n");
            }
        }
    }

    private int randomNumber(){

        int random = 0;
        boolean repeat = true;

        while(repeat){
            random = ThreadLocalRandom.current().nextInt(100000, 999999);
            if(!numbers.contains(random)) repeat = false;
        }

        return random;
    }
}
