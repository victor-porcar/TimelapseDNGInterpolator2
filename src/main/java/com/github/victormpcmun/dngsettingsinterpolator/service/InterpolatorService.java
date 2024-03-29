package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.Setting;
import com.github.victormpcmun.dngsettingsinterpolator.model.SettingRange;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterpolatorService {

    public static final InterpolatorService INSTANCE = new InterpolatorService();
    private static final Map<Integer,DecimalFormat>  DECIMAL_FORMAT_MAP = new HashMap<>();

    static {
        DECIMAL_FORMAT_MAP.put(0,new DecimalFormat("#"));
        DECIMAL_FORMAT_MAP.put(1,new DecimalFormat("#.#"));
        DECIMAL_FORMAT_MAP.put(2,new DecimalFormat("#.##"));
    }


    public Settings calculateInterpolatedSettings(List<SettingRange> settingRangeList, int totalElements, int indexElement) {

        List<Setting> settingList = new ArrayList<>();

        for (SettingRange settingRange: settingRangeList) {
            Setting interpolatedSetting = calculateInterpolatedSetting(settingRange, totalElements, indexElement);
            settingList.add(interpolatedSetting);
        }
        return new Settings(settingList);
    }


    private Setting calculateInterpolatedSetting( SettingRange settingRange , int totalElements, int indexElement) {

        String settingName = settingRange.getSettingName();
        String initValue = settingRange.getValueInit();
        String endValue = settingRange.getValueEnd();
        int decimals = settingRange.getDecimals();
        String value =  calculateInterpolatedValue(totalElements, indexElement, initValue, endValue, decimals);
        return new Setting(settingName, value);
    }


    private String calculateInterpolatedValue(int totalElements, int indexElement, String initValue, String endValue, int decimals) {

        if (indexElement==0) {
            return initValue;
        }

        if (indexElement==totalElements-1) {
            return endValue;
        }

        double initValueAsDouble = Double.parseDouble(initValue);
        double endValueAsDouble = Double.parseDouble(endValue);

        double delta = (endValueAsDouble - initValueAsDouble) / (double) (totalElements-1);
        double value = initValueAsDouble + (delta*indexElement);
        return DECIMAL_FORMAT_MAP.get(decimals).format(value);

    }

}
