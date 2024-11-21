package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** */
public final class SimpleController implements Controller {

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
        return new ArrayList<>(this.history);
    }

    @Override
    public void printString() {
        if (this.stringToPrint == null) {
            throw new IllegalArgumentException("The string to print is unset");
        }
        System.out.println(this.stringToPrint); //NOPMD
        this.history.add(this.stringToPrint);
    }
}
