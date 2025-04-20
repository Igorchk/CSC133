package com.mycompany.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private Media m;

    public Sound(String fileName) {
        try {
            // Loading the sound file as an InputStream
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
            
            if (is != null) {
                // Creating a Media object from the InputStream
                m = MediaManager.createMedia(is, "audio/wav");
                System.out.println("✅ Successfully loaded sound: " + fileName);
            } else {
                System.out.println("❌ Could not find sound file: " + fileName);
            }
        } catch (IOException e) {
            // Handling exceptions (likely due to Codename One Desktop Media limitations)
            System.out.println("❌ Error loading sound file: " + fileName);
            System.out.println("⚠️ Likely due to Codename One Desktop Media limitations.");
            e.printStackTrace();
        }
    }

    public void play() {
        // Check if the media is loaded and then play
        if (m != null) {
            m.setTime(0);  // Restart the sound if it's already playing
            m.play();      // Play the sound
        } else {
            // Handle the error case if sound is not loaded
            System.out.println("⚠️ Cannot play sound — media not loaded.");
        }
    }
}