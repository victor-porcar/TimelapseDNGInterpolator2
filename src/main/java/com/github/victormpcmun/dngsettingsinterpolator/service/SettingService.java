package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.util.StringUtil;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;
import com.github.victormpcmun.dngsettingsinterpolator.model.DNGFile;

import java.io.File;


public class SettingService {

    public static final SettingService INSTANCE = new SettingService();

    private final FileService fileService =  FileService.INSTANCE;
    private final XMPService xmpService = XMPService.INSTANCE;
    private final ByteArrayService byteArrayService = ByteArrayService.INSTANCE;
    private final DNGFileService dngFileService = DNGFileService.INSTANCE;


    public String getSettingValueFromFile(String filePath, String settingName)  {

        DNGFile dngFile = dngFileService.splitDNGFile(filePath);
        String xmp = dngFile.getXmpContentAsString();
        return StringUtil.inBetween(xmp,settingName+"=\"", "\"");

    }


    public void changeSettingValueInFile(String directory, String fileName, Settings settings)  {
        String filePath = directory + File.separator + fileName;
        DNGFile dngFile = dngFileService.splitDNGFile(filePath);
        String newXmpContent = xmpService.calculateNewXmpContent(dngFile.getXmpContentAsString(),  settings);
        byte[] result =byteArrayService.concatenateByteArray(dngFile.getPreviousToXmp(), newXmpContent.getBytes(), dngFile.getAfterXmp());
        fileService.writeFileFromByteArray(filePath, result);
    }

}
