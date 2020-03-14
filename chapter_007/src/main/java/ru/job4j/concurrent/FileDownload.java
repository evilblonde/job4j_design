package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {

    // used https://github.com/evilblonde/job4j_design/blob/6e1cc006ab5ddc4db76261abb4520901597fa147/chapter_007/src/main/resources/bettogh-third-section.mp3?raw=true for testing

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("provide url and maximum speed");
            System.exit(-3);
        }
        String file = args[0];
        int maxSpeed = Integer.parseInt(args[1]) * 1024;
        int bufferLength = 1024;
        int bytesReadInLastSecond = 0;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("music.mp3")) {
            byte[] dataBuffer = new byte[bufferLength];
            int bytesRead;
            long timestamp = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, bufferLength)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                printProgress(fileOutputStream.getChannel().size());
                bytesReadInLastSecond += bytesRead;

                if (bytesReadInLastSecond >= maxSpeed && System.currentTimeMillis() - timestamp < 1000) {
                    Thread.sleep(1000 + timestamp - System.currentTimeMillis());
                    bytesReadInLastSecond -= maxSpeed;
                    timestamp = System.currentTimeMillis();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void printProgress(long size) {
        System.out.print(String.format("\rdownloaded %d kB", size / 1024));
    }
}
