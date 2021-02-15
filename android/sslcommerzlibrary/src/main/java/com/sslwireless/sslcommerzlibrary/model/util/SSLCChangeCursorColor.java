package com.sslwireless.sslcommerzlibrary.model.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

public class SSLCChangeCursorColor {
    private Context context;

    public SSLCChangeCursorColor(Context context) {
        this.context = context;
    }

    public void setCursorColor(EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                field = editor.getClass().getDeclaredField("mDrawableForCursor");
                field.setAccessible(true);
                field.set(editor, drawables);
            } else {
                field = editor.getClass().getDeclaredField("mCursorDrawable");
                field.setAccessible(true);
                field.set(editor, drawables);
            }


            String[] resFieldNames = {"mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes"};
            String[] drawableFieldNames = {"mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter"};
            Integer[] colors = {color, color, color};

            for (int i = 0; i < resFieldNames.length; i++) {
                Integer color1 = colors[i];
                if (color1 == null) {
                    continue;
                }

                String resFieldName = resFieldNames[i];
                String drawableFieldName = drawableFieldNames[i];

                field = TextView.class.getDeclaredField(resFieldName);
                field.setAccessible(true);
                int selectHandleRes = field.getInt(view);

                Resources res = view.getContext().getResources();
                Drawable selectHandleDrawable = res.getDrawable(selectHandleRes).mutate();
                selectHandleDrawable.setColorFilter(color1, PorterDuff.Mode.SRC_IN);

                field = editor.getClass().getDeclaredField(drawableFieldName);
                field.setAccessible(true);
                field.set(editor, selectHandleDrawable);
            }
//            String[]
//                    fieldsNames = { "mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes" },
//                    drawablesNames = { "mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter" };
//
//            for( int index = 0; index < fieldsNames.length && index < drawablesNames.length; index++ ) {
//                String
//                        fieldName = fieldsNames[ index ],
//                        drawableName = drawablesNames[ index ];
//
//                field = TextView.class.getDeclaredField( fieldName );
//                field.setAccessible(true);
//                int handle = field.getInt( view );
//
//                Drawable handleDrawable = ContextCompat.getDrawable(view.getContext(), handle);
//                drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
//
//                field = editor.getClass().getDeclaredField( drawableName );
//                field.setAccessible(true);
//                field.set( editor, handleDrawable );
//            }
        } catch (Exception ignored) {
//            Log.e("Exception", ignored.getLocalizedMessage());
        }
    }
}
