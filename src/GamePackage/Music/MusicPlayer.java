/*
 * Class created by Stylianos Gakis
 * Student of HKR with code STGA0006
 * Created Wednesday, 14/11/2018 at 21:43
 */

package GamePackage.Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements Runnable {

    private String musicFilePath;

    public MusicPlayer(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    @Override
    public void run() {
        playMusic();
    }

    private void playMusic() {
        Clip clip = null;
        try {
            // to store current position
            Long currentFrame;

            // current status of clip
            String status;

            File file = new File("src/" + musicFilePath);
            // create AudioInputStream object
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            // TODO maybe run and interrupt it when we want to interrupt it with clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.loop(0);

            // Start the music
            clip.start();

            /*
            clip.getMicrosecondLength() / 1000 is used to make the Thread work for specifically as much as the
            music file is big.
             */
            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        } finally {
            if (clip != null) { // If it did in fact get initialized.
                clip.stop();
            }
        }
    }
}
