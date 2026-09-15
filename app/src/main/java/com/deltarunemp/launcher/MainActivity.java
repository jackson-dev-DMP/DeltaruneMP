package com.deltarunemp.launcher;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TARGET_EXE_VERSION = "1.1.6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appVersion = BuildConfig.VERSION_NAME;

        if (appVersion.equals(TARGET_EXE_VERSION)) {
            Toast.makeText(this, "DeltaruneMP Launcher v" + appVersion + " Initialized", Toast.LENGTH_SHORT).show();
            
            // Example path to local executable once extracted
            String targetExe = getFilesDir().getAbsolutePath() + "/game/DeltaruneMP.exe";
            WineRunner.launchExe(this, targetExe);
        } else {
            Toast.makeText(this, "Version mismatch! Launcher v" + appVersion + " requires v" + TARGET_EXE_VERSION, Toast.LENGTH_LONG).show();
        }
    }
}
