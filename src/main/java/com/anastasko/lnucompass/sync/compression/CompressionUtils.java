package com.anastasko.lnucompass.sync.compression;

import com.anastasko.lnucompass.sync.converter.CanPrint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressionUtils extends CanPrint {

    public static byte[] compress(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new CompressionException(e);
        }
        byte[] output = outputStream.toByteArray();
        print("Original: " + data.length);
        print("Compressed: " + output.length);
        return output;
    }
    public static byte[] decompress(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (Exception e) {
            throw new CompressionException(e);
        }
        byte[] output = outputStream.toByteArray();
        print("Original: " + data.length);
        print("Decompressed: " + output.length);
        return output;
    }
}
