public interface AlphabetGame {

    /**
     * checks if the available letters in the composition can be used to make the given word. 
     * @param letterComposition the letters available. 
     * @param word the word to make.
     * @return true if the word can be made.
     */
    boolean canMakeWord(String letterComposition,String word);

}
