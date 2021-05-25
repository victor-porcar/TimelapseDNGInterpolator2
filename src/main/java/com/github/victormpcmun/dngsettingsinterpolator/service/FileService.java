package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileService {

    public static final FileService INSTANCE = new FileService();

    public byte[] readFileAsByteArray(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new ExecutionException("Can not read file:" + filePath, e);
        }
    }

    public void writeFileFromByteArray(String filePath, byte[] result) {
        try {
            Files.write(Paths.get(filePath), result);
        } catch (IOException e) {
            throw new ExecutionException("Can not write file:" + filePath, e);
        }
    }

}
