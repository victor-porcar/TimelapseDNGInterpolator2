package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.CommandLineArguments;
import com.github.victormpcmun.dngsettingsinterpolator.model.InterpolationBlock;
import com.github.victormpcmun.dngsettingsinterpolator.model.SettingRange;
import com.github.victormpcmun.dngsettingsinterpolator.model.Settings;

import java.util.List;

public class ExecutionService {

    public static final ExecutionService INSTANCE = new ExecutionService();
    private static final String ALL_PROPERTIES_FILE = "allProperties.txt";

    ResourcesService resourcesService = ResourcesService.INSTANCE;
    DirectoryService directoryService = DirectoryService.INSTANCE;
    InterpolatorService interpolatorService = InterpolatorService.INSTANCE;
    SettingService settingService = SettingService.INSTANCE;
    BackupService backupService = BackupService.INSTANCE;
    SettingRangeService settingRangeService = SettingRangeService.INSTANCE;
    MessageService messageService = MessageService.INSTANCE;
    FileValidationService fileValidationService = FileValidationService.INSTANCE;

    public void execute(CommandLineArguments commandLineArguments) {
        messageService.message("DNG Settings Interpolator");
        messageService.message("=========================");
        if (!commandLineArguments.isParametersOK()) {
            abortNoError("Parameters are not set correctly.");
        }

        if (commandLineArguments.isViewSettings()) {
            messageService.message(resourcesService.getResourcesFile(ALL_PROPERTIES_FILE));
            abortNoError();
        }

        go(commandLineArguments);
    }

    private void go(CommandLineArguments commandLineArguments) {

        List<InterpolationBlock> interpolationBlockList = commandLineArguments.getInterpolationBlockList();
        boolean allFilesExist = fileValidationService.validateAllFilesExistOrAbort(commandLineArguments.getWorkingDirectory(), interpolationBlockList);
        if (!allFilesExist) {
            abortError("At least one file does not exist");
        }

        for (InterpolationBlock interpolationBlock: interpolationBlockList) {
            processBlock(commandLineArguments, interpolationBlock);
        }
    }


    private void processBlock(CommandLineArguments commandLineArguments, InterpolationBlock interpolationBlock) {

        String initFile = interpolationBlock.getInitFile();
        String endFile = interpolationBlock.getEndFile();

        List<String> settingNamesList = calculateSettingNames(commandLineArguments);
        String workingDirectory = commandLineArguments.getWorkingDirectory();
        String backupDirectory = commandLineArguments.getBackupDirectory();

        List<SettingRange> settingRangeList = settingRangeService.calculateSettingRangeList(workingDirectory, interpolationBlock, settingNamesList);

        List<String> files = directoryService.getFilesPathInBetween(workingDirectory,interpolationBlock);
        messageService.emptyLine();
        messageService.message("Procession block from  " + initFile + " to " + endFile + " => " + files.size() + " files");
        int filesCount = files.size();



        for (int fileIndex=0; fileIndex<filesCount;fileIndex++) {
            String fileName = files.get(fileIndex);
            backupService.backupFile(workingDirectory, fileName, backupDirectory);
            processFile(workingDirectory, fileName, settingRangeList, filesCount, fileIndex);
        }
        messageService.message("End => " + "Total files updated in this block:" + filesCount);
    }


    private void processFile(String workingDirectory, String fileName, List<SettingRange> settingRangeList,  int filesCount, int fileIndex) {
        Settings settings = interpolatorService.calculateInterpolatedSettings(settingRangeList, filesCount, fileIndex);
        settingService.changeSettingValueInFile(workingDirectory, fileName, settings);
        messageService.message("Processed File => " + fileName);
    }


    private List<String> calculateSettingNames(CommandLineArguments commandLineArguments) {
        if (commandLineArguments.isAllSettings()) {
            return resourcesService.getResourcesFileAsListOfLines(ALL_PROPERTIES_FILE);
        }
        return commandLineArguments.getSettingNames();
    }

    public void abortError(String text) {
        messageService.message(text);
        System.exit(-1);
    }

    public void abortNoError(String text) {
        messageService.messageError(text);
        System.exit(1);
    }

    public void abortNoError() {
        System.exit(1);
    }
}
