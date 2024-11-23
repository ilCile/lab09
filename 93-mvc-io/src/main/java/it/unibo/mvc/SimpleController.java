package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** */
public final class SimpleController implements Controller {

    private final File file = new File("." + System.getProperty("file.separator") + "history.txt");
    private String stringToPrint;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setStringToPrint(final String string) {
        this.stringToPrint = Objects.requireNonNull(string);
    }

    @Override
    public String getStringToPrint() {
        return Objects.requireNonNull(this.stringToPrint);
    }

    @Override
    public List<String> getPrintHistory() {
        //return new ArrayList<>(this.history);
        List<String> list = new ArrayList<>();
        try (BufferedReader b = new BufferedReader(new FileReader(file))) {
            for(int i = 0; i < this.history.size(); i++) {
                list.add(b.readLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); //NOPMD
        }
        return list;
    }

    @Override
    public void printString() {
        if (this.stringToPrint == null) {
            throw new IllegalArgumentException("The string to print is unset");
        }
        System.out.println(this.stringToPrint); //NOPMD
        this.history.add(this.stringToPrint);
        try (DataOutputStream w = new DataOutputStream(new FileOutputStream(file, true))) {
            w.writeChars(this.stringToPrint + "\n");
        } catch (final IOException e) {
            System.out.println(e.getMessage()); //NOPMD
        }
    }
}
