package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.Setting;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;
import com.github.victormpcmun.dngsettingsinterpolator.util.StringUtil;

import static com.github.victormpcmun.dngsettingsinterpolator.util.StringUtil.replaceInBetween;

public class XMPService {

    public static final XMPService INSTANCE = new XMPService();
    public static final String ATTRIBUTE_TO_BE_ADJUSTED = "xmp:CreatorTool";

    public String calculateNewXmpContent(String xmpContent,  Settings settings) {

        String result = xmpContent;

        for (Setting setting: settings.getSettingList()) {
            result = replaceInBetween(result,setting.getName()+"=\"","\"", setting.getValue());
        }

        /*
        the updated XMP must have the same size of the origin XMP content, otherwise the DNG file is corrupted
        the following is a trick to achieve this
        a not really used value setting is adjusted by removing or adding characters
        the chosen setting is the name of the ATTRIBUTE_TO_BE_ADJUSTED

         */


        String ensuredSizeResult = ensureSize(result, xmpContent.length(), ATTRIBUTE_TO_BE_ADJUSTED);

        return ensuredSizeResult;

    }


    private String ensureSize(String result, int expectedSize, String settingNameToAdjustValue) {

        int delta = expectedSize - result.length();
        if (delta==0) {
            return result;
        }

        String settingNameToAdjustAndValue = StringUtil.inBetweenIncluding(result,  settingNameToAdjustValue + "=\"", "\"");
        String settingNameToAdjustAndValueInProperSize = adjustValueForSettingToHaveExactSize(settingNameToAdjustAndValue, delta);
        String resultWithProperSize = result.replace(settingNameToAdjustAndValue, settingNameToAdjustAndValueInProperSize);
        return resultWithProperSize;
    }

    private String adjustValueForSettingToHaveExactSize(String propertyAndValue, int charactersIncrementSize ) {

        if (charactersIncrementSize==0) {
            return propertyAndValue;
        }

        String inBetweenQuotes = StringUtil.inBetweenExcluding(propertyAndValue,"=\"", "\"");
        String newInBetweenQuotes;
        if (charactersIncrementSize>0) {
            // add
            newInBetweenQuotes = inBetweenQuotes + StringUtil.getRepeatedString("A", charactersIncrementSize);
        } else  {
            // remove
            newInBetweenQuotes = inBetweenQuotes.substring(0,inBetweenQuotes.length()+charactersIncrementSize);
        }
        String result = propertyAndValue.replace(inBetweenQuotes,newInBetweenQuotes);
        return result;
    }

}
