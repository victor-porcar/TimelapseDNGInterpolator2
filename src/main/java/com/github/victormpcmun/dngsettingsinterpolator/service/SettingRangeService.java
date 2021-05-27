package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.InterpolationBlock;
import com.github.victormpcmun.dngsettingsinterpolator.model.SettingRange;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SettingRangeService {

    public static final SettingRangeService INSTANCE = new SettingRangeService();

    public static final int DECIMALS = 2;

    SettingService settingService = SettingService.INSTANCE;
    MessageService messageService = MessageService.INSTANCE;



    public List<SettingRange> calculateSettingRangeList(String path, InterpolationBlock interpolationBlock, List<String> settingNameList) {

        String initFile = interpolationBlock.getInitFile();
        String endFile = interpolationBlock.getEndFile();

        List<SettingRange> settingRangeList= new ArrayList<>();
        for (String setting: settingNameList) {

            String initValue = settingService.getSettingValueFromFile(path + File.separator + initFile, setting);
            String endValue = settingService.getSettingValueFromFile(path + File.separator + endFile, setting);

            if (hasToCalculateSetting(initValue, endValue)) {
                settingRangeList.add(new SettingRange(setting, initValue, endValue, DECIMALS));
            } else {
                messageService.message(
                        "Skipping setting " + setting + " because its value is not defined in " + initFile + " or " + endFile);
            }



        }
        return settingRangeList;
    }


    private boolean hasToCalculateSetting(String initValue, String endValue)  {
        return initValue!=null && endValue!=null;
    }

}
