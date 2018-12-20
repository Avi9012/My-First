package com.example.lenovo.sangam3;

import android.content.Context;
import android.app.Activity;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import org.w3c.dom.Text;


public class Validation {

    private static final int MIN_LENGTH = 6;
    private Context context;

    public Validation(Context context)
    {
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message)
    {
        String matter = textInputEditText.getText().toString().trim();
        if(matter.isEmpty())
        {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else
        {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message)
    {
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches())
        {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else
        {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message)
    {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if(!value1.contentEquals(value2))
        {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        }
        else
        {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyboardFrom(View view)
    {
        InputMethodManager mm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        mm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
