package utils.fonts;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * FontCache utility class used for caching custom fonts.
 */
public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontName, Context context){
        Typeface fontTypeface = fontCache.get(fontName);

        if (fontTypeface == null) {
            try {
                fontTypeface = Typeface.createFromAsset(context.getAssets(), fontName);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontName, fontTypeface);
        }

        return fontTypeface;
    }
}
