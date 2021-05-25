package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.util.StringUtil;
import com.github.victormpcmun.dngsettingsinterpolator.model.Setting;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;

public class XMPService {

    public static final XMPService INSTANCE = new XMPService();
    public static final String FLOATING_NOT_USED_XMP_ATTRIBUTE = "xmp:CreatorTool";

    private ExecutionService executionService = ExecutionService.INSTANCE;

    public String calculateNewXmpContent(String xmpContent,  Settings settings) {

        String result = xmpContent;

        for (Setting setting: settings.getSettingList()) {
            result= StringUtil.replaceInBetween(result,setting.getName()+"=\"","\"", setting.getValue());
        }

        String ensuredSizeResult = ensureSize(result, xmpContent.length());

        return ensuredSizeResult;

    }


    private String ensureSize(String result, int expectedSize) {

        int delta = expectedSize - result.length();
        if (delta==0) {
            return result;
        }

        String floatingNotUsedPropertyAndValue = StringUtil.inBetweenIncluding(result, FLOATING_NOT_USED_XMP_ATTRIBUTE + "=\"", "\"");
        String pfloatingNotUsedPropertyAndValueInProperSize = makeSettingAndValueHaveExactSize(floatingNotUsedPropertyAndValue, delta);
        String resultWithProperSize = result.replace(floatingNotUsedPropertyAndValue, pfloatingNotUsedPropertyAndValueInProperSize);
        return resultWithProperSize;
    }

    private String makeSettingAndValueHaveExactSize(String propertyAndValue, int charactersIncrementSize ) {

        if (charactersIncrementSize==0) {
            return propertyAndValue;
        }

        String inBetweenQuotes = StringUtil.inBetween(propertyAndValue,"=\"", "\"");
        String newInBetweenQuotes = propertyAndValue;
        if (charactersIncrementSize>0) {
            // add
            newInBetweenQuotes = inBetweenQuotes + StringUtil.getRepeatedString("A", charactersIncrementSize);
        } else if (charactersIncrementSize<0) {
            // remove
            newInBetweenQuotes = inBetweenQuotes.substring(0,inBetweenQuotes.length()+charactersIncrementSize);
        }
        String result = propertyAndValue.replace(inBetweenQuotes,newInBetweenQuotes);
        return result;
    }

}
