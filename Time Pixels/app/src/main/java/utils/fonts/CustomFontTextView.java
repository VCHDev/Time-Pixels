package utils.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Custom Font  class used for loading custom fonts to TextView
 */
public class CustomFontTextView extends TextView{
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFonts(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFonts(context, attrs);
    }

    private void applyCustomFonts(Context context, AttributeSet attributeSet) {
        int textStyle = attributeSet.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface typeface = selectTypeface(context, textStyle);
        setTypeface(typeface);
    }

    private Typeface selectTypeface(Context context, int textStyle) {

        switch (textStyle) {
            case Typeface.BOLD:
                return FontCache.getTypeface("fonts/open.sans/OpenSans-Bold.ttf",context);

            case Typeface.ITALIC:
                return FontCache.getTypeface("fonts/open.sans/OpenSans-Italic.ttf", context);

            case Typeface.BOLD_ITALIC:
                return FontCache.getTypeface("fonts/open.sans/OpenSans-BoldItalic.ttf", context);

            case Typeface.NORMAL:
                default:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Regular.ttf", context);
        }
    }

    
}
