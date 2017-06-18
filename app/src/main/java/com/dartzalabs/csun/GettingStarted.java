package com.dartzalabs.csun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;

public class GettingStarted extends AppIntro {


    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(GettingStartedSlideTemplate.newInstance(R.layout.getting_started_1));
        addSlide(GettingStartedSlideTemplate.newInstance(R.layout.getting_started_2));
        addSlide(GettingStartedSlideTemplate.newInstance(R.layout.getting_started_3));
    }



    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {
    }


    @SuppressWarnings("unused")
    public void onstart(View v){
        loadMainActivity();
    }
}
