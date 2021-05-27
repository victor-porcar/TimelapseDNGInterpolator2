package com.github.victormpcmun.dngsettingsinterpolator.model;

import java.util.List;

public class Settings {

    private final List<Setting> settingList;
    public Settings(List<Setting> settingList) {
        this.settingList = settingList;
    }
    public List<Setting> getSettingList() {
        return settingList;
    }

    @Override
    public String toString() {
        return "[" + settingList + ")";
    }
}
