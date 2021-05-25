package com.github.victormpcmun.dngsettingsinterpolator.model;

public class DNGFile {
    byte[] previousToXmp;
    byte[] xmpContent;
    byte[] afterXmp;
    String xmpContentAsString;


    public DNGFile(byte[] previousToXmp, byte[] xmpContent, byte[] afterXmp, String xmpContentAsString) {
        this.previousToXmp = previousToXmp;
        this.xmpContent = xmpContent;
        this.afterXmp = afterXmp;
        this.xmpContentAsString = xmpContentAsString;
    }

    public byte[] getPreviousToXmp() {
        return previousToXmp;
    }

    public byte[] getAfterXmp() {
        return afterXmp;
    }

    public String getXmpContentAsString() {
        return xmpContentAsString;
    }
}
