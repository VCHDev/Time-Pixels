package utils.fonts.foctory;

import android.util.Log;

/**
 * Font Factory for creating fonts.
 */
public class FontFacotry {
    public static final String TAG = FontFacotry.class.getSimpleName();

    public static FontType getFontType(String fontType) {
        Log.d(TAG, "-----------" + OpenSans.class.getSimpleName());
        if (fontType.equalsIgnoreCase("OPENSANS")  ) {
            return  new OpenSans();
        } else if (fontType.equalsIgnoreCase(Roboto.class.getSimpleName())) {
            return new Roboto();
        } else if (fontType.equalsIgnoreCase(RobotoCondensed.class.getSimpleName())){
            return  new RobotoCondensed();
        }

        return null;
    }
}
