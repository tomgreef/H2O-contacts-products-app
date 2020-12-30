package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class Home extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}