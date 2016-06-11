package utils.fonts.foctory;

import android.content.Context;
import android.graphics.Typeface;

/**
 * FontTypefaceOpenSans Interface used for Factory fonts style.
 */
public interface FontType {

    public Typeface getTypeFace(Context context, String fontName,  int textStyle);
}
