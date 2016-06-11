package utils.fonts.foctory;

import android.content.Context;
import android.graphics.Typeface;

import utils.fonts.FontCache;
import utils.fonts.type.FontTypefaceRoboto;

/**
 * Roboto font type.
 */
public class Roboto implements FontType {

    @Override
    public Typeface getTypeFace(Context context, String fontName,  int textStyle) {
        switch (textStyle) {
            case FontTypefaceRoboto.BOLD:
                return FontCache.getTypeface("Roboto-Black.ttf", context);

            case FontTypefaceRoboto.BOLDITALIC:
                return FontCache.getTypeface("Roboto-BlackItalic.ttf", context);

            case FontTypefaceRoboto.BLACK:
                return FontCache.getTypeface("Roboto-Bold.ttf", context);

            case FontTypefaceRoboto.BLACKITALIC:
                return FontCache.getTypeface("Roboto-BoldItalic.ttf", context);

            case FontTypefaceRoboto.ITALIC:
                return FontCache.getTypeface("Roboto-Italic.ttf", context);

            case FontTypefaceRoboto.LIGHT:
                return FontCache.getTypeface("Roboto-Light.ttf", context);

            case FontTypefaceRoboto.LIGHTITALIC:
                return FontCache.getTypeface("Roboto-LightItalic.ttf", context);

            case FontTypefaceRoboto.MEDIUM:
                return FontCache.getTypeface("Roboto-Medium.ttf", context);

            case FontTypefaceRoboto.MEDIUMITALIC:
                return FontCache.getTypeface("Roboto-MediumItalic.ttf", context);

            case FontTypefaceRoboto.THIN:
                return FontCache.getTypeface("Roboto-Thin.ttf", context);

            case FontTypefaceRoboto.THINITALIC:
                return FontCache.getTypeface("Roboto-ThinItalic.ttf", context);

            case FontTypefaceRoboto.REGULAR:
            default:
                return FontCache.getTypeface("Roboto-Regular.ttf", context);
        }
    }
}
