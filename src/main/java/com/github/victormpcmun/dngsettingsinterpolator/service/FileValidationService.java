package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.InterpolationBlock;

import java.util.List;

public class FileValidationService {

    public static final FileValidationService INSTANCE = new FileValidationService();

    private FileService fileService = FileService.INSTANCE;
    private ExecutionService executionService = ExecutionService.INSTANCE;

    public void validateAllFilesExistOrAbort(String directory, List<InterpolationBlock> interpolationBlockList) {

        for (InterpolationBlock interpolationBlock: interpolationBlockList) {
            boolean existInitFile = fileService.existFile(directory, interpolationBlock.getInitFile());
            if (existInitFile) {
                executionService.abortError("File " + interpolationBlock.getInitFile() + " does not exist");
            }

            boolean existEndFile = fileService.existFile(directory, interpolationBlock.getEndFile());
            if (existEndFile) {
                executionService.abortError("File " + interpolationBlock.getEndFile() + " does not exist");
            }
        }
    }
}
