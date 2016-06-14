package utils.fonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import edu.todo.vch.timepixels.R;

/**
 * Custom Font  class used for loading custom fonts to TextView
 */
public class CustomFontTextView extends TextView {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    private final class FontTypeface {
        private static final int NORMAL = 0;
        private static final int BOLD = 1;
        private static final int BOLD_ITALIC = 2;
        private static final int ITALIC = 3;
        private static final int EXTRA_BOLD = 4;
        private static final int EXTRA_BOLD_ITALIC = 5;
        private static final int LIGHT = 6;
        private static final int LIGHT_ITALIC = 7;
        private static final int SEMIBOLD = 8;
        private static final int SEMIBOLD_ITALIC = 9;
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFonts(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFonts(context, attrs);
    }

    private void applyCustomFonts(Context context, AttributeSet attributeSet) {
        TypedArray attributeArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);
        int textStyle = attributeArray.getInt(R.styleable.CustomFontTextView_textStyle, 0);

        if (textStyle == 0) {
            textStyle = attributeSet.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        }

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName,  int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_name_RobotoCondensed))){
            return FontCache.getTypeface("fonts/roboto.condensed/RobotoCondensed-LightItalic.ttf",context);
        }

        if (fontName.contentEquals(context.getString(R.string.font_name_OpenSansBold))) {
            switch (textStyle) {
                case FontTypeface.BOLD:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Bold.ttf", context);

                case FontTypeface.ITALIC:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Italic.ttf", context);

                case FontTypeface.BOLD_ITALIC:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-BoldItalic.ttf", context);

                case FontTypeface.EXTRA_BOLD:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-ExtraBold.ttf", context);

                case FontTypeface.EXTRA_BOLD_ITALIC:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-ExtraBoldItalic.ttf", context);

                case FontTypeface.LIGHT:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Light.ttf", context);

                case FontTypeface.LIGHT_ITALIC:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-LightItalic.ttf", context);

                case FontTypeface.SEMIBOLD:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Semibold.ttf", context);

                case FontTypeface.SEMIBOLD_ITALIC:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-SemiboldItalic.ttf", context);

                case FontTypeface.NORMAL:
                default:
                    return FontCache.getTypeface("fonts/open.sans/OpenSans-Regular.ttf", context);
            }
        }  else {
            return  null;
        }
    }

    
}
