package HW2;

import java.util.ArrayList;

/**
 * Class that models a Dulcimer. Currently, only the bass strings are represented.
 * 
 * This program is to be modified in Part2.
 * 
 * @author Jerome Bustarga - JHB09808
 * @version 09.23.2024
 */
public class Dulcimer {
    public ArrayList<DulcimerString> bassStrings;
    public ArrayList<DulcimerString> treble1Strings;
    public ArrayList<DulcimerString> treble2Strings;

    /**
     * Constructs a Dulcimer with the specified bass, treble1, and treble2 strings.
     * 
     * @param bassNotes    a String specifying the bass notes, from bottom to top
     * @param treble1Notes a String specifying the treble1 notes, from bottom to top
     * @param treble2Notes a String specifying the treble2 notes, from bottom to top
     */
    public Dulcimer(String bassNotes, String treble1Notes, String treble2Notes) {
        this.bassStrings = createStringList(bassNotes);
        this.treble1Strings = createStringList(treble1Notes);
        this.treble2Strings = createStringList(treble2Notes);
    }

    /**
     * Creates a list of DulcimerString objects from a string of notes.
     *
     * @param notes A string containing the notes separated by spaces.
     * @return An ArrayList of DulcimerString objects.
     */
    private ArrayList<DulcimerString> createStringList(String notes) {
        ArrayList<DulcimerString> strings = new ArrayList<>();
        for (String str : notes.split("\\s+")) {
            strings.add(new DulcimerString(str));
        }
        return strings;
    }

    /**
     * Strikes the specified string and sets it to vibrating.
     * 
     * @param stringNum the string number (starting at the bottom with 0)
     * @param type      the type of string (bass, treble1, or treble2)
     */
    public void hammer(int stringNum, String type) {
        ArrayList<DulcimerString> strings;
        switch (type) {
            case "bass":
                strings = bassStrings;
                break;
            case "treble1":
                strings = treble1Strings;
                break;
            case "treble2":
                strings = treble2Strings;
                break;
            default:
                return;
        }
        if (stringNum >= 0 && stringNum < strings.size()) {
            strings.get(stringNum).strike();
        }
    }

    /**
     * Plays the sounds corresponding to all of the struck strings.
     */
    public void play() {
        double combinedFrequencies = 0.0;
        for (DulcimerString string : bassStrings) {
            combinedFrequencies += string.sample();
        }
        for (DulcimerString string : treble1Strings) {
            combinedFrequencies += string.sample();
        }
        for (DulcimerString string : treble2Strings) {
            combinedFrequencies += string.sample();
        }
        StdAudio.play(combinedFrequencies);
    }
}
