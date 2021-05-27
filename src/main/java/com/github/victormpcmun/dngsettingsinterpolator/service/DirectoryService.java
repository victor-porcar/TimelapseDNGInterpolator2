package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.InterpolationBlock;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryService {

    public static final DirectoryService INSTANCE = new DirectoryService();
    public final ExecutionService executionService = ExecutionService.INSTANCE;

    public List<String> getFilesPathInBetween(String workingDirectory, InterpolationBlock interpolationBlock) {

        List<String> filesNamesInDirectory = getFilesNamesInDirectory(workingDirectory);

        int indexInitFileName = filesNamesInDirectory.indexOf(interpolationBlock.getInitFile().toLowerCase());
        int indexEndFileName = filesNamesInDirectory.indexOf(interpolationBlock.getEndFile().toLowerCase());

        if (indexInitFileName<0 || indexEndFileName<0) {
            executionService.abortError("Init file Or End file does not exist");
        }

        List<String> listOfFilesInRange = filesNamesInDirectory.subList(indexInitFileName, indexEndFileName+1);
        return listOfFilesInRange;
    }


    private List<String> getFilesNamesInDirectory(String directoryPath) {

        List<File> listOfFiles = getFilesInDirectory(directoryPath);
        List<String> stringList = new ArrayList<>();

        for (File file : listOfFiles) {
            stringList.add(file.getName().toLowerCase());
        }
        return stringList;
    }


    private List<File> getFilesInDirectory(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] arrayOfFiles = folder.listFiles();
        if (arrayOfFiles!=null) {
            fileList.addAll(toFileList(arrayOfFiles));
        }
        return fileList;
    }

    private List<File> toFileList(File[] fileArray) {
        List<File> listOfFiles = new ArrayList<>();
        Arrays.sort(fileArray);
        for (File file : fileArray) {
            if (file.isFile()) {
                listOfFiles.add(file);
            }
        }
        return listOfFiles;
    }
}
