package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayService {

    public static final ByteArrayService INSTANCE = new ByteArrayService();

    //he Knuth-Morris-Pratt Pattern Matching Algorithm can be used to search a byte array.
    // from http://helpdesk.objects.com.au/java/search-a-byte-array-for-a-byte-sequence

    public  int indexOf(int from, byte[] data, byte[] pattern) {
        int[] failure = computeFailure(pattern);

        int j = 0;

        for (int i = from; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) {
                j++;
            }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    /**
     * Computes the failure function using a boot-strapping process,
     * where the pattern is matched against itself.
     */
    private int[] computeFailure(byte[] pattern) {
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

        byte result[] = outputStream.toByteArray();
        return result;
    }
}
