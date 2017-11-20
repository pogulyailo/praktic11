import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilesApp {

    public static void main(String[] args) throws Exception {

        String content = new Scanner(new File("links.txt")).useDelimiter("\\Z").next();
        System.out.println(content);

        String[] parts = content.split("\n");

        for (int i = 0; i < parts.length; i++){
           System.out.println( " :) " + parts [i]);
           newFile(parts [i], "cache/name" + i);
       }
    }

    public static void newFile(String link, String saveTo) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        URL website = new URL(link);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(saveTo);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }
}