package com.github.victormpcmun.dngsettingsinterpolator.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryService {

    public static final DirectoryService INSTANCE = new DirectoryService();
    public final ExecutionService executionService = ExecutionService.INSTANCE;

    public List<String> getFilesPathInBetween(String workingDirectory, String initFileName, String endFileName) {

        List<File> listOfFiles = getFilesInDirectory(workingDirectory);
        List<String> stringList = new ArrayList<>();

        for (File file : listOfFiles) {
            stringList.add(file.getName().toLowerCase());
        }


        int indexInitFileName = stringList.indexOf(initFileName.toLowerCase());
        int indexEndFileName = stringList.indexOf(endFileName.toLowerCase());

        if (indexInitFileName<0 || indexEndFileName<0) {
            executionService.abortError("Init file Or End file does not exist");
        }

        List<String> subString = stringList.subList(indexInitFileName, indexEndFileName+1);
        return subString;
    }


    private List<File> getFilesInDirectory(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles!=null) {
            Arrays.sort(listOfFiles);

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
}
