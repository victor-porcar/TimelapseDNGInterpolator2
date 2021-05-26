package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.SettingRange;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SettingRangeService {


    public static final SettingRangeService INSTANCE = new SettingRangeService();

    public static final int DECIMALS = 2;

    SettingService settingService = SettingService.INSTANCE;
    MessageService messageService = MessageService.INSTANCE;



    public List<SettingRange> calculateSettingRangeList(String path, String initFile, String endFile, List<String> settingNameList) {
        List<SettingRange> settingRangeList= new ArrayList<>();
        for (String setting: settingNameList) {

            String initValue = settingService.getSettingValueFromFile(path + File.separator + initFile, setting);
            String endValue = settingService.getSettingValueFromFile(path + File.separator + endFile, setting);

            if (initValue==null) {
                messageService.message("Setting " + setting + " is not defined in file " + initFile);
                continue;
            }

            if (endValue==null) {
                messageService.message("Setting " + setting + " is not defined in file " + endFile);
                continue;
            }

            if (initValue.equals(endValue)) {
                messageService.message("Setting " + setting + " does not change");
                continue;
            }

            settingRangeList.add(new SettingRange(setting, initValue, endValue, DECIMALS));


        }
        return settingRangeList;
    }

}
