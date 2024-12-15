package HW2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Class that models a string on a Dulcimer.
 * 
 * This program is to be completed in Part 1.
 * Be sure to document appropriately.
 * 
 * @author Jerome Bustarga - JHB09808
 * @version 09.23.2024
 */
public class DulcimerString {
    private Queue<Double> stringQueue;
    private String note;
    private static final double DECAY_FACTOR = 0.996;
    private static final String[] CHROMATIC_SCALE = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    /**
     * Constructs a DulcimerString with the specified note.
     * 
     * @param note the musical note for this string
     */
    public DulcimerString(String note) {
        this.note = note;
        int queueSize = calculateQueueSize();
        stringQueue = new LinkedList<>();
        for (int i = 0; i < queueSize; i++) {
            stringQueue.add(0.0);
        }
    }

    /**
     * Gets the note of this DulcimerString.
     * 
     * @return the note of this DulcimerString
     */
    public String getNote() {
        return note;
    }

    /**
     * Gets the offset of this note from middle C.
     * 
     * @return the offset from middle C
     */
    public int getOffsetFromMiddleC() {
        String baseNote = note.replaceAll("[+\\-]", "");
        int index = java.util.Arrays.asList(CHROMATIC_SCALE).indexOf(baseNote);
        int offset = index - 3;
        offset += 12 * (note.length() - baseNote.length()) / 2;
        return offset;
    }

    /**
     * Strikes the string, causing it to vibrate.
     */
    public void strike() {
        Random rand = new Random();
        int size = stringQueue.size();
        stringQueue.clear();
        for (int i = 0; i < size; i++) {
            stringQueue.add(rand.nextDouble() - 0.5);
        }
    }

    /**
     * Samples the current sound of the string.
     * 
     * @return the current sound sample of the string
     */
    public double sample() {
        if (stringQueue.isEmpty()) {
            return 0.0;
        }
        double front = stringQueue.remove();
        double newSample = DECAY_FACTOR * 0.5 * (front + stringQueue.peek());
        stringQueue.add(newSample);
        return front;
    }

    /**
     * Returns the size of the string queue.
     * 
     * @return the size of the string queue
     */
    public int size() {
        return stringQueue.size();
    }

    /**
     * Gets the value at the specified index in the string queue.
     * 
     * @param index the index of the value to retrieve
     * @return the value at the specified index
     */
    public double get(int index) {
        return ((LinkedList<Double>) stringQueue).get(index);
    }

    /**
     * Calculates the size of the queue based on the note's offset from middle C.
     * 
     * @return the calculated queue size
     */
    private int calculateQueueSize() {
        int offsetFromMiddleC = getOffsetFromMiddleC();
        return (int) Math.round(StdAudio.SAMPLE_RATE / (440.0 * Math.pow(2, offsetFromMiddleC / 12.0)));
    }

    /**
     * Returns a string representation of this DulcimerString.
     * 
     * @return a string representation of this DulcimerString
     */
    @Override
    public String toString() {
        return "DulcimerString: " + note + " (Size: " + size() + ")";
    }
}
