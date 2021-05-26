package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayService {

    public static final ByteArrayService INSTANCE = new ByteArrayService();

    // The Knuth-Morris-Pratt Pattern Matching Algorithm
    // https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm

    public  int indexOf(int from, byte[] data, byte[] pattern) {
        int[] failure = failure(pattern);
        int j = 0;

        for (int index = from; index < data.length; index++) {
            while (j > 0 && pattern[j] != data[index]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[index]) {
                j++;
            }
            if (j == pattern.length) {
                return index - pattern.length + 1;
            }
        }
        return -1;
    }


    private int[] failure(byte[] pattern) {
        int[] failure = new int[pattern.length];
        int j = 0;

        for (int i = 1; i < pattern.length; i++) {
            while (j>0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }
        return failure;
    }



    public byte[] concatenateByteArray(byte[] ...chunks) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (byte[] chunk: chunks) {
            try {
                outputStream.write(chunk);
            } catch (IOException e) {
                throw new ExecutionException("Can not concatenate byte array chunks", e);
            }
        }

        byte[] result = outputStream.toByteArray();
        return result;
    }
}
