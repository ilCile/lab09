package it.unibo.mvc;

import java.util.List;

/** */
public interface Controller {

    /** Set the string to print.
     *  @param s is the string to print
     */
    void setStringToPrint(String s);

    /** get the current string that is about to get printed.
     * @return the string
     */
    String getStringToPrint();

    /** get the history of the printed strings.
     * @return the list of printed strings
     */
    List<String> getPrintHistory();

    /** print in stdout the current string. */
    void printString();
}
