package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private AdminDB_Manager db = new AdminDB_Manager(this);
    Spinner mLanguage;
    TextView mTextView;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        db.open();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClient(view);
            }
        });

        // Cambio de idioma
        mLanguage = (Spinner) findViewById(R.id.spLanguage);
        mTextView = (TextView) findViewById(R.id.language);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_option));
        mLanguage.setAdapter(mAdapter);
        int language;

        if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("en")) {
            mLanguage.setSelection(mAdapter.getPosition("English"));
            language = 0;
        } else {
            mLanguage.setSelection(mAdapter.getPosition("Spanish"));
            language = 1;
        }

        mLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean refresh = false;

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Context context;
                Resources resources;
                int set = language;

                if(refresh && set != i) {
                    recreate();
                    refresh = false;
                    set = i;
                    Toast.makeText(adapterView.getContext(),
                            getString(R.string.language_select) + " " + adapterView.getItemAtPosition(i).toString(),
                            Toast.LENGTH_LONG).show();
                }

                switch (i) {
                    case 0:
                        context = LocaleHelper.setLocale(MainActivity.this, "en");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.language_selected));
                        refresh = true;
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(MainActivity.this, "es");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.language_selected));
                        refresh = true;
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                refresh = false;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    public void addClient(android.view.View v) {
        Intent intent = new Intent(this, AddClient.class);
        startActivity(intent);
    }
}