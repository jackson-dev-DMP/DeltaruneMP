package com.deltarunemp.launcher;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TARGET_EXE_VERSION = "1.1.6";

    private Spinner containerSpinner;
    private Spinner icpSpinner;
    private Button launchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerSpinner = findViewById(R.id.containerSpinner);
        icpSpinner = findViewById(R.id.icpSpinner);
        launchButton = findViewById(R.id.launchButton);

        // Populate Containers Dropdown
        String[] containers = {"Default (Wine 8.12)", "Beta (Wine 9.0-beta)"};
        ArrayAdapter<String> containerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, containers);
        containerSpinner.setAdapter(containerAdapter);

        // Populate ICP Touch Control Profiles
        String[] icpProfiles = {"Deltarune.icp", "Custom_Controls.icp"};
        ArrayAdapter<String> icpAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, icpProfiles);
        icpSpinner.setAdapter(icpAdapter);

        launchButton.setOnClickListener(v -> {
            String appVersion = BuildConfig.VERSION_NAME;

            if (appVersion.equals(TARGET_EXE_VERSION)) {
                String selectedContainer = containerSpinner.getSelectedItem().toString();
                String selectedIcp = icpSpinner.getSelectedItem().toString();
                
                Toast.makeText(this, "Launching with " + selectedContainer + " & " + selectedIcp, Toast.LENGTH_SHORT).show();

                String targetExe = getFilesDir().getAbsolutePath() + "/game/DeltaruneMP.exe";
                WineRunner.launchExe(this, targetExe);
            } else {
                Toast.makeText(this, "Version mismatch! Target is " + TARGET_EXE_VERSION, Toast.LENGTH_LONG).show();
            }
        });
    }
}
