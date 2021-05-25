package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.DNGFile;

import java.util.Arrays;

public class DNGFileService {

    public static final DNGFileService INSTANCE = new DNGFileService();
    public static final String TAG_XMPMETA_OPEN = "<x:xmpmeta";
    public static final String TAG_XMPMETA_CLOSE = "</x:xmpmeta>";

    FileService fileService = FileService.INSTANCE;
    ByteArrayService byteArrayService = ByteArrayService.INSTANCE;


    public DNGFile splitDNGFile(String filePath)  {

        byte[] arrayBytes = fileService.readFileAsByteArray(filePath);

        int init = byteArrayService.indexOf(0,arrayBytes, TAG_XMPMETA_OPEN.getBytes());
        int end =  byteArrayService.indexOf(init, arrayBytes, TAG_XMPMETA_CLOSE.getBytes());
        end = end +  TAG_XMPMETA_CLOSE.getBytes().length;

        byte[] xmpFile = Arrays.copyOfRange(arrayBytes, init, end);
        String xmpContentAsString =  new String(xmpFile);

        byte[] previousToXmp =  Arrays.copyOfRange(arrayBytes, 0, init);

        byte[] afterXmp = Arrays.copyOfRange(arrayBytes, end, arrayBytes.length);
        DNGFile dngFile = new DNGFile(previousToXmp, xmpFile, afterXmp,xmpContentAsString);
        return dngFile;
    }

}
