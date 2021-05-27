package com.github.victormpcmun.dngsettingsinterpolator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandLineArguments {

    private static final Integer WORKING_DIRECTORY_POSITION =0;
    private static final Integer BACKUP_DIRECTORY_POSITION =1;
    private static final Integer INIT_FILE_POSITION =2;
    private static final Integer END_FILE_POSITION =3;
    private static final Integer SETTING_NAMES_POSITION =4;
    private static final Integer VIEW_SETTINGS_POSITION =0;

    private final List<String>  argList = new ArrayList<>();
    private final boolean parametersOK;

    public CommandLineArguments(String[] argsArray) {
        Collections.addAll(argList, argsArray);
        parametersOK = (argList.size()==1 && argList.get(VIEW_SETTINGS_POSITION).equals("--settings")) || (argList.size()>=(SETTING_NAMES_POSITION +1));

    }

    public boolean isParametersOK() {
        return parametersOK;
    }

    public boolean isViewSettings() {
        return argList.get(VIEW_SETTINGS_POSITION).equals("--settings");
    }

    public boolean isAllSettings() {
        return argList.get(SETTING_NAMES_POSITION).equals("-all");
    }

    public List<InterpolationBlock> getInterpolationBlockList() {
        return null;
    }

    public List<String> getSettingNames() {
        return argList.subList(SETTING_NAMES_POSITION,argList.size());
    }

    public String getWorkingDirectory() {
        return argList.get(WORKING_DIRECTORY_POSITION);
    }

    public String getBackupDirectory() {
        return argList.get(BACKUP_DIRECTORY_POSITION);
    }

}
