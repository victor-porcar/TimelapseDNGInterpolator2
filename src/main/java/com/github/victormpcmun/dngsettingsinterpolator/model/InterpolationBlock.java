package com.github.victormpcmun.dngsettingsinterpolator.model;

public class InterpolationBlock {

    String initFile;
    String endFile;

    public InterpolationBlock(String initFile, String endFile) {
        this.initFile = initFile;
        this.endFile = endFile;
    }

    public String getInitFile() {
        return initFile;
    }

    public String getEndFile() {
        return endFile;
    }

}
