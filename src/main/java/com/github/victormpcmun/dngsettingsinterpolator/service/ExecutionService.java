package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.CommandLineArguments;
import com.github.victormpcmun.dngsettingsinterpolator.model.SettingRange;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;

import java.util.List;

public class ExecutionService {
    public static final ExecutionService INSTANCE = new ExecutionService();
    public static final String ALL_PROPERTIES_FILE = "allProperties.txt";
    public static final String HELP_FILE = "help.txt";

    ResourcesService resourcesService = ResourcesService.INSTANCE;
    DirectoryService directoryService = DirectoryService.INSTANCE;
    InterpolatorService interpolatorService = InterpolatorService.INSTANCE;
    SettingService settingService = SettingService.INSTANCE;
    BackupService backupService = BackupService.INSTANCE;
    SettingRangeService settingRangeService = SettingRangeService.INSTANCE;

    public void execute(CommandLineArguments commandLineArguments) {
        if (!commandLineArguments.isParametersOK()) {
            abortNoError("Parameters are not set correctly. Please execute --help");
        }

        if (commandLineArguments.isHelp()) {
            System.out.println(getHelpMessage());
            abortNoError();
        }

        go(commandLineArguments);
    }


    private void go(CommandLineArguments commandLineArguments) {

        String initFile = commandLineArguments.getInitFile();
        String endFile = commandLineArguments.getEndFile();
        List<String> settingNamesList = calculateSettingNames(commandLineArguments);
        String workingDirectory = commandLineArguments.getWorkingDirectory();
        String backupDirectory = commandLineArguments.getBackupDirectory();

        List<SettingRange> settingRangeList = settingRangeService.calculateSettingRangeList(workingDirectory, initFile, endFile, settingNamesList);

        List<String> files = directoryService.getFilesPathInBetween(workingDirectory,initFile, endFile);
        int filesCount = files.size();

        for (int fileIndex=0; fileIndex<filesCount;fileIndex++) {
            backupService.backupFile(workingDirectory, files.get(fileIndex), backupDirectory);
            Settings settings = interpolatorService.calculateInterpolatedSettings(settingRangeList,filesCount, fileIndex);
            String fileName = files.get(fileIndex);
            settingService.changeSettingValueInFile(workingDirectory, fileName, settings);
            System.out.println("File => " + fileName + " " + settings);
        }
        System.out.println("End => " + "Total files updated:" + filesCount);
    }



    private List<String> calculateSettingNames(CommandLineArguments commandLineArguments) {
        if (commandLineArguments.isAllSettings()) {
            return resourcesService.getResourcesFileAsListOfLines(ALL_PROPERTIES_FILE);
        }
        return commandLineArguments.getSettingNames();
    }

    public void abortError(String text) {
        System.err.println(text);
        System.exit(-1);
    }

    public void abortNoError(String text) {
        System.err.println(text);
        System.exit(1);
    }

    public void abortNoError() {
        System.exit(1);
    }


    private String getHelpMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(resourcesService.getResourcesFile(HELP_FILE)).append(System.lineSeparator());
        sb.append("List of settings:").append(System.lineSeparator());
        sb.append(resourcesService.getResourcesFile(ALL_PROPERTIES_FILE));
        return sb.toString();

    }
}
