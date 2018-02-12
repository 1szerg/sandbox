package com.gmail.user0abc.sandbox.fileDownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class FileLoader {

    public static void main(String[] args){
        try {
            URL remote = new URL("http://localhost:8085/examples/soap.xml");
            File local = File.createTempFile("download", ".txt");
            downloadFile(remote, local);
            System.out.println("Saved to "+local.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public static File downloadFile(URL remote, File local) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(remote.openStream());
        FileOutputStream fos = new FileOutputStream(local);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return local;
    }

    public static File vanillaDownload(URL remote, File local) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(remote.openStream());
        FileOutputStream fos = new FileOutputStream(local);
        int len = -1;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1){
            fos.write(buffer, 0, len);
        }
        inputStream.close();
        fos.close();
        return local;
    }

}
