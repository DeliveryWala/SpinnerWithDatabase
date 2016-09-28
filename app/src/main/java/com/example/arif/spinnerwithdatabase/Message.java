package com.example.arif.spinnerwithdatabase;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Arif on 28/09/16.
 */
public class Message {
    public static void message(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
