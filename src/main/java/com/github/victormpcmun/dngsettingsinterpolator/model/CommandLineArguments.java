package com.github.victormpcmun.dngsettingsinterpolator.model;

import java.util.ArrayList;
import java.util.List;

public class CommandLineArguments {

    private static final Integer WORKING_DIRECTORY_POSTTION =0;
    private static final Integer BACKUP_DIRECTORY_POSTTION =1;
    private static final Integer INIT_FILE_POSTTION =2;
    private static final Integer END_FILE__POSTTION=3;
    private static final Integer SETTING_NAMES_POSITION =4;
    private static final Integer HELP_POSITION=0;

    private List<String>  argList;
    private boolean parametersOK;

    public CommandLineArguments(String[] argsArray) {
        this.argList = new ArrayList<>();
        for (String s:argsArray) {
            argList.add(s);
        }

        parametersOK = (argList.size()==1 && argList.get(HELP_POSITION).equals("--help")) || (argList.size()>=(SETTING_NAMES_POSITION +1));

    }

    public boolean isParametersOK() {
        return parametersOK;
    }

    public boolean isHelp() {
        return argList.get(HELP_POSITION).equals("--help");
    }

    public boolean isAllSettings() {
        return argList.get(SETTING_NAMES_POSITION).equals("*");
    }

    public String getInitFile() {
        return argList.get(INIT_FILE_POSTTION);
    }

    public String getEndFile() {
        return argList.get(END_FILE__POSTTION);
    }

    public List<String> getSettingNames() {
        return argList.subList(SETTING_NAMES_POSITION,argList.size());
    }

    public String getWorkingDirectory() {
        return argList.get(WORKING_DIRECTORY_POSTTION);
    }

    public String getBackupDirectory() {
        return argList.get(BACKUP_DIRECTORY_POSTTION);
    }

}
