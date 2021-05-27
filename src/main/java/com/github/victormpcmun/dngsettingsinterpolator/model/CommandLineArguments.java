package com.github.victormpcmun.dngsettingsinterpolator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandLineArguments {

    private static final String FILES = "--files";
    private static final String SETTINGS = "--settings";
    private static final String VIEW_ALL_SETTINGS = "--view_all_settings";


    private static final Integer WORKING_DIRECTORY_POSITION =0;
    private static final Integer VIEW_ALL_SETTINGS_POSITION =0;
    private static final Integer BACKUP_DIRECTORY_POSITION =1;

    private static final Integer BEGIN_FILES_OR_SETTINGS_ARGS_POSITION = BACKUP_DIRECTORY_POSITION + 1;


    private List<String> argList;
    private List<String> fileNames;
    private List<String> settingsName;


    public CommandLineArguments(String[] argsArray) {
        argList = new ArrayList<>();
        Collections.addAll(argList, argsArray);
        fileNames = new ArrayList<>();
        settingsName = new ArrayList<>();
        analyzeArgs(argList);
    }

    public boolean isParametersOK() {
        return argList.size()>=5;
    }

    public boolean isViewSettings() {
        return argList.get(VIEW_ALL_SETTINGS_POSITION).equals(VIEW_ALL_SETTINGS);
    }

    public boolean isAllSettings() {
        return settingsName.isEmpty();
    }

    public List<InterpolationBlock> getInterpolationBlockList() {
        return getInterpolationBlockList(fileNames);
    }

    public List<String> getSettingNames() {
        return settingsName;
    }

    public String getWorkingDirectory() {
        return argList.get(WORKING_DIRECTORY_POSITION);
    }

    public String getBackupDirectory() {
        return argList.get(BACKUP_DIRECTORY_POSITION);
    }


    private void analyzeArgs(List<String> argsList) {
        boolean fileSeries=false;
        boolean settingSeries=false;
        for (int index = BEGIN_FILES_OR_SETTINGS_ARGS_POSITION; index < argsList.size(); index++) {
            String arg = argsList.get(index);
            if (FILES.equals(arg)) {
                fileSeries=true;
                settingSeries=false;
                continue;
            }

            if (SETTINGS.equals(arg)) {
                fileSeries=false;
                settingSeries=true;
                continue;
            }

            if (fileSeries) {
                fileNames.add(arg);
            }

            if (settingSeries) {
                settingsName.add(arg);
            }
        }
    }

    private List<InterpolationBlock> getInterpolationBlockList(List<String> fileNames) {
        List<InterpolationBlock> interpolationBlockList = new ArrayList<>();
        int size = fileNames.size();
        for (int index=0; index < size; index++) {
            if (index<size-1) {
                interpolationBlockList.add(new InterpolationBlock(fileNames.get(index), fileNames.get(index+1)));
            }
        }
        return interpolationBlockList;
    }

}
