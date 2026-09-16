package com.deltarunemp.launcher;

import android.content.Context;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class WineRunner {

    public static void launchExe(Context context, String exePath) {
        try {
            File filesDir = context.getFilesDir();
            File usrDir = new File(filesDir, "usr");
            File winePrefix = new File(filesDir, ".wine");

            ProcessBuilder pb = new ProcessBuilder();
            
            // Define execution environment for Box64 + Wine
            Map<String, String> env = pb.environment();
            env.put("TMPDIR", filesDir.getAbsolutePath() + "/tmp");
            env.put("PATH", usrDir.getAbsolutePath() + "/bin:" + System.getenv("PATH"));
            env.put("LD_LIBRARY_PATH", usrDir.getAbsolutePath() + "/lib");
            env.put("WINEPREFIX", winePrefix.getAbsolutePath());
            env.put("BOX64_LOG", "1");
            env.put("BOX86_LOG", "1");

            // Execute box64 running wine64 with the target executable
            pb.command(
                usrDir.getAbsolutePath() + "/bin/box64",
                usrDir.getAbsolutePath() + "/bin/wine",
                exePath
            );

            pb.redirectErrorStream(true);
            Process process = pb.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
