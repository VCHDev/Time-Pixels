package utils.fonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import edu.todo.vch.timepixels.R;
import utils.fonts.foctory.FontFacotry;
import utils.fonts.foctory.FontType;
import utils.fonts.type.FontTypefaceOpenSans;

/**
 * Custom Font  class used for loading custom fonts to TextView
 */
public class CustomFontTextView extends TextView{
    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFonts(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFonts(context, attrs);
    }

    private void applyCustomFonts(Context context, AttributeSet attributeSet) {

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomFontTextView);
        String attributeName = typedArray.getString(R.styleable.CustomFontTextView_font);

//        int typeFace = attributeSet.getAttributeListValue(FontTypefaceOpenSans.REGULAR, FontTypefaceOpenSans.getOptionFontTypes() , FontTypefaceOpenSans.BOLD);
        int typeFace = attributeSet.getAttributeIntValue(ANDROID_SCHEMA, "textStyle" , FontTypefaceOpenSans.EXTRABOLD);
        FontType fontType = FontFacotry.getFontType("OPENSANS");

        Typeface customFonts = fontType.getTypeFace(context, attributeName, typeFace);
        setTypeface(customFonts);
    }

}
