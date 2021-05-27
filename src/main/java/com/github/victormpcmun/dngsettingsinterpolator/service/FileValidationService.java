package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.model.InterpolationBlock;

import java.util.List;

public class FileValidationService {

    public static final FileValidationService INSTANCE = new FileValidationService();

    private FileService fileService = FileService.INSTANCE;
    private MessageService messageService = MessageService.INSTANCE;

    public boolean validateAllFilesExistOrAbort(String directory, List<InterpolationBlock> interpolationBlockList) {
        boolean result=true;
        for (InterpolationBlock interpolationBlock: interpolationBlockList) {
            boolean existInitFile = fileService.existFile(directory, interpolationBlock.getInitFile());
            if (!existInitFile) {
                messageService.messageError("File " + interpolationBlock.getInitFile() + " does not exist");
                result = false;
            }

            boolean existEndFile = fileService.existFile(directory, interpolationBlock.getEndFile());
            if (!existEndFile) {
                messageService.messageError("File " + interpolationBlock.getEndFile() + " does not exist");
                result = false;
            }
        }
        return result;
    }
}
