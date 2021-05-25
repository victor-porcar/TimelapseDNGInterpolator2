package com.github.victormpcmun.dngsettingsinterpolator.model;

public class Setting {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +  name + "=\"" + value + "\"" +'}';
    }
}
