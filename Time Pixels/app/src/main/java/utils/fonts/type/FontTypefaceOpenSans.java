package utils.fonts.type;

/**
 * Open-Sans types.
 */
public final class FontTypefaceOpenSans {
    public static final int BOLD = 0;
    public static final int BOLDITALIC = 1;
    public static final int EXTRABOLD = 2;
    public static final int EXTRABOLDITALIC = 3;
    public static final int ITALIC = 4;
    public static final int LIGHT = 5;
    public static final int LIGHTITALIC = 6;
    public static final int REGULAR = 7;
    public static final int SEMIBOLD = 8;
    public static final int SEMIBOLDITALIC = 9;

    public static String[] getOptionFontTypes() {
        return new String[]{
                "bold",
                "bolfitalic",
                "extrabold",
                "extrabolditalic",
                "italic",
                "light",
                "lightitalic",
                "regular",
                "semibold",
                "semibolditalic"
        };
    }
}
