package com.github.victormpcmun.dngsettingsinterpolator.model;

public class SettingRange {

    private final String settingName;
    private final String valueInit;
    private final String valueEnd;
    private final int decimals;

    public SettingRange(String settingName, String valueInit, String valueEnd, int decimals) {
        this.settingName = settingName;
        this.valueInit=valueInit;
        this.valueEnd=valueEnd;
        this.decimals=decimals;
    }

    public String getSettingName() {
        return settingName;
    }
    public String getValueInit() {
        return valueInit;
    }
    public String getValueEnd() {
        return valueEnd;
    }
    public int getDecimals() {
        return decimals;
    }
}
