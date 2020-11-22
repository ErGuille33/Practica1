package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("se supone que ha asdasdasdasd el render");
        _surface = new MySurfaceView(this);

        setContentView(_surface);

    }

    @Override
    protected void onResume() {
        super.onResume();
        _surface.resume();
    }

    @Override
    protected void onPause() {

        super.onPause();
        _surface.pause();

    }

    protected MySurfaceView _surface;
}
