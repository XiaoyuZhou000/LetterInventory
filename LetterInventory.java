import java.util.*;

// Xiaoyu Zhou
// TA: Hitesh Boinpally
// The LetterInventory class can be used to keep track
// of an inventory of letters of the English alphabet.
public class LetterInventory {
    // The global constant value used in the whole class.
    public static final int INITIAL_CAPACITY = 26;

    private int[] elementData; // list of characters
    private int size;          // current sum of all of the counts in this inventory

    // Constructs an inventory (a count) of the alphabetic letters in the empty string
    public LetterInventory() {
        this("");
    }

    // Constructs an inventory (a count) of the alphabetic letters in the given string, 
    // ignoring the case of letters and ignoring any non-alphabetic characters.
    // Parameters:
    //      String data - the string want to count alphabetic letters
    public LetterInventory(String data) {
        elementData = new int[INITIAL_CAPACITY];
        size = 0;
        String lowerData = data.toLowerCase();
        for (int i = 0; i < lowerData.length(); i++) {
            char c = lowerData.charAt(i);
            if (c - 'a' >= 0 && c - 'a' <= 25) {
                elementData[c - 'a']++;
                size++;
            }
        }
    }

    // Calculate and Returns a count of how many of this letter (case-insensitive) are in 
    // the inventory.
    // Parameters:
    //      char letter - the character you want get the count from
    public int get(char letter) {
        char lowerLetter = Character.toLowerCase(letter);
        checkLetter(lowerLetter);
        return elementData[(int) lowerLetter - 'a'];
    }

    // Sets the count for the given letter (case-insensitive) to the given value.
    // Parameters:
    //      char letter - the character you want to reset
    //      int value - the value you want to set to the specific letter 
    public void set(char letter, int value) {
        char lowerLetter = Character.toLowerCase(letter);
        checkLetter(lowerLetter);
        checkValue(value);
        size += value - get(letter);
        elementData[(int) lowerLetter - 'a'] = value;
    }

    // Calculate and Returns the sum of all of the counts in this inventory.
    public int size() {
        return size;
    }

    // Returns true if this inventory is empty (i.e. all counts are 0).
    public boolean isEmpty() {
        return (size == 0);
    }

    // Returns a string representation of the inventory with the letters all in lowercase 
    // and in sorted order and surrounded by square brackets.
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            String result = "[";
            for (int counter = 0; counter < INITIAL_CAPACITY; counter++) {
                for (int repeat = elementData[counter]; repeat > 0; repeat--) {
                    result += (char) ('a' + counter);
                }
                
            }
            result += "]";
            return result;
        }
    }

    // Constructs and returns a new LetterInventory object that represents the sum of this
    // LetterInventory and the other given LetterInventory.
    // Parameters:
    //      LetterInventory other - the other LetterInventory object you want to be added.
    public LetterInventory add(LetterInventory other) {
        return calculation(other, 1);
    }

    // Constructs and returns a new LetterInventory object that represents the result of 
    // subtracting the other inventory from this inventory
    // Parameters:
    //      LetterInventory other - the other LetterInventory object you want 
    //                              to be subtracted.
    public LetterInventory subtract(LetterInventory other) {
        return calculation(other, -1);
    }

    // Check whether the given letter is a alphabetic character
    // If a nonalphabetic character is passed, it will throw an IllegalArgumentException.
    // Parameters:
    //      char letter - the char you want to wheck whether it is a alphabetic letter
    // throw:
    //      IllegalArgumentException if pass in a nonalphabetic character
    private void checkLetter(char letter) {
        if (!(letter - 'a' >= 0 && letter - 'a' <= 25)) {
            throw new IllegalArgumentException(letter + "is not a letter");
        }
    }

    // Check whether the given value is negative
    // If a nonalphabetic character is passed, it will throw an IllegalArgumentException.
    // throw:
    //      IllegalArgumentException if pass in a nonalphabetic character
    // Parameters:
    //      int value - the int value you want to check whether it is negative
    private void checkValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(value + "cannot be negative");
        }
    }

    // Constructs the sum or difference of two inventory of the same letter from
    // different LetterInventory object.
    // Return a new LetterInventory object with the new inventory number
    // Parameters:
    //      LetterInventory other - the other LetterInventory object you want to add or
    //                              subtract from
    //      int addOrMinus - the int to represent whether this method should process adding
    //                       calculation or subtracting calculation
    public LetterInventory calculation(LetterInventory other, int addOrMinus) {
        LetterInventory calLetter = new LetterInventory();
        for (int repeat = 0; repeat < INITIAL_CAPACITY; repeat++) {
            calLetter.elementData[repeat] = this.elementData[repeat] + 
                                           (other.elementData[repeat] * addOrMinus);
            if (calLetter.elementData[repeat] < 0) {
                return null;
            }
            calLetter.size += this.elementData[repeat] + (other.elementData[repeat] * addOrMinus);
        }
        return calLetter;
    }
}
