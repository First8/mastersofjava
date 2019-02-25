public class Soundex  {

    /**
     * This is a default mapping of the 26 letters used in US English. A value of <code>0</code> for a letter position
     * means do not encode.
     * <p>
     * (This constant is provided as both an implementation convenience and to allow Javadoc to pick
     * up the value for the constant values page.)
     * </p>
     *
     * @see #US_ENGLISH_MAPPING
     */
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";

    /**
     * This is a default mapping of the 26 letters used in US English. A value of <code>0</code> for a letter position
     * means do not encode.
     *
     * @see Soundex#Soundex(char[])
     */
    private static final char[] US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();

    /**
     * An instance of Soundex using the US_ENGLISH_MAPPING mapping.
     *
     * @see #US_ENGLISH_MAPPING
     */
    public static final Soundex US_ENGLISH = new Soundex();

    /**
     * The maximum length of a Soundex code - Soundex codes are only four characters by definition.
     *
     * @deprecated This feature is not needed since the encoding size must be constant. Will be removed in 2.0.
     */
    @Deprecated
    private int maxLength = 4;

    /**
     * Every letter of the alphabet is "mapped" to a numerical value. This char array holds the values to which each
     * letter is mapped. This implementation contains a default map for US_ENGLISH
     */
    private final char[] soundexMapping;

    /**
     * Creates an instance using US_ENGLISH_MAPPING
     *
     * @see Soundex#Soundex(char[])
     * @see Soundex#US_ENGLISH_MAPPING
     */
    public Soundex() {
        this.soundexMapping = US_ENGLISH_MAPPING;
    }

    /**
     * Creates a soundex instance using the given mapping. This constructor can be used to provide an internationalized
     * mapping for a non-Western character set.
     *
     * Every letter of the alphabet is "mapped" to a numerical value. This char array holds the values to which each
     * letter is mapped. This implementation contains a default map for US_ENGLISH
     *
     * @param mapping
     *                  Mapping array to use when finding the corresponding code for a given character
     */
    public Soundex(final char[] mapping) {
        this.soundexMapping = new char[mapping.length];
        System.arraycopy(mapping, 0, this.soundexMapping, 0, mapping.length);
    }

    /**
     * Creates a refined soundex instance using a custom mapping. This constructor can be used to customize the mapping,
     * and/or possibly provide an internationalized mapping for a non-Western character set.
     *
     * @param mapping
     *            Mapping string to use when finding the corresponding code for a given character
     * @since 1.4
     */
    public Soundex(final String mapping) {
        this.soundexMapping = mapping.toCharArray();
    }



    /**
     * Encodes an Object using the soundex algorithm. This method is provided in order to satisfy the requirements of
     * the Encoder interface, and will throw an EncoderException if the supplied object is not of type java.lang.String.
     *
     * @param obj
     *                  Object to encode
     * @return An object (or type java.lang.String) containing the soundex code which corresponds to the String
     *             supplied.
     * @throws IllegalArgumentException
     *                  if the parameter supplied is not of type java.lang.String
     *                  if a character is not mapped
     */
    public Object encode(final Object obj) throws IllegalArgumentException {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Parameter supplied to Soundex encode is not of type java.lang.String");
        }
        return soundex((String) obj);
    }

    /**
     * Encodes a String using the soundex algorithm.
     *
     * @param str
     *                  A String object to encode
     * @return A Soundex code corresponding to the String supplied
     * @throws IllegalArgumentException
     *                  if a character is not mapped
     */
    public String encode(final String str) {
        return soundex(str);
    }

    /**
     * Used internally by the SoundEx algorithm.
     *
     * Consonants from the same code group separated by W or H are treated as one.
     *
     * @param str
     *                  the cleaned working string to encode (in upper case).
     * @param index
     *                  the character position to encode
     * @return Mapping code for a particular character
     * @throws IllegalArgumentException
     *                  if the character is not mapped
     */
    private char getMappingCode(final String str, final int index) {
        // map() throws IllegalArgumentException
        final char mappedChar = this.map(str.charAt(index));
        // HW rule check
        if (index > 1 && mappedChar != '0') {
            final char hwChar = str.charAt(index - 1);
            if ('H' == hwChar || 'W' == hwChar) {
                final char preHWChar = str.charAt(index - 2);
                final char firstCode = this.map(preHWChar);
                if (firstCode == mappedChar || 'H' == preHWChar || 'W' == preHWChar) {
                    return 0;
                }
            }
        }
        return mappedChar;
    }

    /**
     * Returns the maxLength. Standard Soundex
     *
     * @deprecated This feature is not needed since the encoding size must be constant. Will be removed in 2.0.
     * @return int
     */
    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    /**
     * Returns the soundex mapping.
     *
     * @return soundexMapping.
     */
    private char[] getSoundexMapping() {
        return this.soundexMapping;
    }

    /**
     * Maps the given upper-case character to its Soundex code.
     *
     * @param ch
     *                  An upper-case character.
     * @return A Soundex code.
     * @throws IllegalArgumentException
     *                  Thrown if <code>ch</code> is not mapped.
     */
    private char map(final char ch) {
        final int index = ch - 'A';
        if (index < 0 || index >= this.getSoundexMapping().length) {
            throw new IllegalArgumentException("The character is not mapped: " + ch);
        }
        return this.getSoundexMapping()[index];
    }

    /**
     * Sets the maxLength.
     *
     * @deprecated This feature is not needed since the encoding size must be constant. Will be removed in 2.0.
     * @param maxLength
     *                  The maxLength to set
     */
    @Deprecated
    public void setMaxLength(final int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Retrieves the Soundex code for a given String object.
     *
     * @param str
     *                  String to encode using the Soundex algorithm
     * @return A soundex code for the String supplied
     * @throws IllegalArgumentException
     *                  if a character is not mapped
     */
    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        final char out[] = {'0', '0', '0', '0'};
        char last, mapped;
        int incount = 1, count = 1;
        out[0] = str.charAt(0);
        // getMappingCode() throws IllegalArgumentException
        last = getMappingCode(str, 0);
        while (incount < str.length() && count < out.length) {
            mapped = getMappingCode(str, incount++);
            if (mapped != 0) {
                if (mapped != '0' && mapped != last) {
                    out[count++] = mapped;
                }
                last = mapped;
            }
        }
        return new String(out);
    }

}





