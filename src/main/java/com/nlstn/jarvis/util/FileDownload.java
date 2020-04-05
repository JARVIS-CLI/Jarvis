package com.nlstn.jarvis.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.nlstn.jarvis.modules.logging.Logger;

public class FileDownload {

    public static void download(String source, String target) {
        try {
            URL url = new URL(source);

            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(target);

            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            fileOutputStream.close();

            Logger.info("Successfully downloaded file " + target);
        } catch (MalformedURLException e) {
            Logger.error("Source " + source + " could not be parsed as URL!", e);
        } catch (IOException e) {
            Logger.error("Error while downloading from " + source, e);
        }
    }
}