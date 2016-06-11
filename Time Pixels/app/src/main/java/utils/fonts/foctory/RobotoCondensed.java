package utils.fonts.foctory;

import android.content.Context;
import android.graphics.Typeface;

import utils.fonts.FontCache;
import utils.fonts.type.FontTypefaceRobotoCondensed;

/**
 * RobotoCondensed font type.
 */
public class RobotoCondensed implements FontType {

    @Override
    public Typeface getTypeFace(Context context, String fontName,  int textStyle) {

        switch (textStyle) {

            case FontTypefaceRobotoCondensed.BOLD:
                return FontCache.getTypeface("RobotoCondensed-Bold.ttf", context);

            case FontTypefaceRobotoCondensed.BOLDITALIC:
                return FontCache.getTypeface("RobotoCondensed-BoldItalic.ttf", context);

            case FontTypefaceRobotoCondensed.ITALIC:
                return FontCache.getTypeface("RobotoCondensed-Italic.ttf", context);

            case FontTypefaceRobotoCondensed.LIGHT:
                return FontCache.getTypeface("RobotoCondensed-Light.ttf", context);

            case FontTypefaceRobotoCondensed.LIGHTITALIC:
                return FontCache.getTypeface("RobotoCondensed-LightItalic.ttf", context);

            case FontTypefaceRobotoCondensed.REGULAR:
            default:
                return FontCache.getTypeface("RobotoCondensed-Regular.ttf", context);
        }
    }
}
