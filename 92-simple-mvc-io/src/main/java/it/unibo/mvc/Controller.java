package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    File currentFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");

    public void setCurrentFile(File newFile){
        this.currentFile = newFile;
    }

    public File getCurrentFile(){
        return this.currentFile;
    }

    public String getPath(){
        return this.currentFile.getPath();
    }

    public void write(final String data) throws IOException{
        try (PrintStream ps = new PrintStream(getPath(), StandardCharsets.UTF_8)) {
            ps.println(data);
        } catch (IOException e) {
            System.out.println("ERRORE: " + e.getMessage());
        }
    }
}
