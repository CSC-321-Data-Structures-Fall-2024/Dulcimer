package HW2;

import java.awt.Font;

/**
 * Driver class for the keyboard-based virtual dulcimer.
 * Currently only has the bass keys.
 * 
 * This program is modified in Part2.
 * 
 * @author Jerome Bustarga - JHB09808
 * @version 09.23.2024
 */
public class DulcimerDriver {
    /**
     * The main method to run the virtual dulcimer.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String bassKeys =   "a   s   d   f   g   h   j   k   l   ;   '";       
        String treble1Keys = "q   w   e   r   t   y   u   i   o   p   [";
        String treble2Keys = "1   2   3   4   5   6   7   8   9   0   -";
        String dashes =     "--- --- --- --- --- --- --- --- --- --- ---";
        String bassNotes =   "G-  A   B   C   D   E   F   G   A+ A#+  C+";
        String treble1Notes = "G   A   B   C   D   E   F   G   A   B   C+";
        String treble2Notes = "G+  A+  B   C+  D+  E+  F+  G+  A+  B   C++";
        
        StdDraw.setFont(new Font("Monospaced", Font.PLAIN, 12));
        StdDraw.textLeft(0.00, 1.00, "DULCIMER KEY MAPPINGS");
        StdDraw.textLeft(0.00, 0.95, "TREBLE2 keys:  " + treble2Keys);
        StdDraw.textLeft(0.00, 0.92, "        notes: " + treble2Notes);
        StdDraw.textLeft(0.00, 0.89, "TREBLE1 keys:  " + treble1Keys);
        StdDraw.textLeft(0.00, 0.86, "        notes: " + treble1Notes);
        StdDraw.textLeft(0.00, 0.83, "BASS    keys:  " + bassKeys);
        StdDraw.textLeft(0.00, 0.80, "        notes: " + bassNotes);
        
        String bassKeyMap = bassKeys.replace(" ","");
        String treble1KeyMap = treble1Keys.replace(" ","");
        String treble2KeyMap = treble2Keys.replace(" ","");
        
        Dulcimer dulc = new Dulcimer(bassNotes, treble1Notes, treble2Notes);
        while (true) { 
            if (StdDraw.hasNextKeyTyped()) {
                char keyTyped = Character.toLowerCase(StdDraw.nextKeyTyped());
                int bassIndex = bassKeyMap.indexOf(keyTyped);
                int treble1Index = treble1KeyMap.indexOf(keyTyped);
                int treble2Index = treble2KeyMap.indexOf(keyTyped);
                
                if (bassIndex != -1) {
                    dulc.hammer(bassIndex, "bass");
                } else if (treble1Index != -1) {
                    dulc.hammer(treble1Index, "treble1");
                } else if (treble2Index != -1) {
                    dulc.hammer(treble2Index, "treble2");
                }
            }
            dulc.play();
        }
    }    
}
