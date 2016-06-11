package utils.fonts.foctory;

import android.content.Context;
import android.graphics.Typeface;

import utils.fonts.FontCache;

/**
 * OpenSans font type.
 */
public class OpenSans implements FontType {

    @Override
    public Typeface getTypeFace(Context context, String fontName,  int textStyle) {

        switch (fontName) {
            case "font.name.OpenSansBold":
                return FontCache.getTypeface("/fonts/open/sans/OpenSans-Bold.ttf", context);

//            case FontTypefaceOpenSans.BOLDITALIC:
//                return FontCache.getTypeface("OpenSans-BoldItalic.ttf", context);
//
//            case FontTypefaceOpenSans.EXTRABOLD:
//                return FontCache.getTypeface("OpenSans-ExtraBold.ttf", context);
//
//            case FontTypefaceOpenSans.EXTRABOLDITALIC:
//                return FontCache.getTypeface("OpenSans-ExtraBoldItalic.ttf", context);
//
//            case FontTypefaceOpenSans.ITALIC:
//                return FontCache.getTypeface("OpenSans-Italic.ttf", context);
//
//            case FontTypefaceOpenSans.LIGHT:
//                return FontCache.getTypeface("OpenSans-Light.ttf", context);
//
//            case FontTypefaceOpenSans.LIGHTITALIC:
//                return FontCache.getTypeface("OpenSans-LightItalic.ttf", context);
//
//            case FontTypefaceOpenSans.SEMIBOLD:
//                return FontCache.getTypeface("OpenSans-Semibold.ttf", context);
//
//            case FontTypefaceOpenSans.SEMIBOLDITALIC:
//                return FontCache.getTypeface("OpenSans-SemiboldItalic.ttf", context);
//
//            case FontTypefaceOpenSans.REGULAR:
//            default:
//                return FontCache.getTypeface("OpenSans-Regular.ttf", context);
        }
        return null;
    }
}
