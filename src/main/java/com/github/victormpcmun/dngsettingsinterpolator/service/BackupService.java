package com.github.victormpcmun.dngsettingsinterpolator.service;

import java.io.File;

public class BackupService {
    public static final BackupService INSTANCE = new BackupService();

    FileService fileService = FileService.INSTANCE;

    public void backupFile(String workingDirectory, String fileName, String backupDirectory) {
        String filePath = workingDirectory + File.separator+fileName;
        byte[] content = fileService.readFileAsByteArray(filePath);

        String backupFilePath = backupDirectory + File.separator+fileName;
        fileService.writeFileFromByteArray(backupFilePath, content);
    }
}
