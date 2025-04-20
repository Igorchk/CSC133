package com.mycompany.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.IOException;
import java.io.InputStream;

public class BGSound {
    private Media m;

    public BGSound(String fileName) {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
            m = MediaManager.createMedia(is, "audio/wav"); // true = stream mode
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (m != null) m.play();
    }

    public void pause() {
        if (m != null) m.pause();
    }
}