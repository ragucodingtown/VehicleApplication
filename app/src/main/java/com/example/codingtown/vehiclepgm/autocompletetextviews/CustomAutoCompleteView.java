package com.example.codingtown.vehiclepgm.autocompletetextviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by CodingTown on 22-12-2016.
 */
public class CustomAutoCompleteView extends AutoCompleteTextView
{

    public CustomAutoCompleteView(Context context) {
        super(context);
    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAutoCompleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {

        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }

    @Override
    protected void replaceText(CharSequence text) {
        super.replaceText(text);
    }
}
