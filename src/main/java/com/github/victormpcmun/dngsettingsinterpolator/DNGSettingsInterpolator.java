package com.github.victormpcmun.dngsettingsinterpolator;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;
import com.github.victormpcmun.dngsettingsinterpolator.model.CommandLineArguments;
import com.github.victormpcmun.dngsettingsinterpolator.service.ExecutionService;

public class DNGSettingsInterpolator {
    public static void main(String[] args) {

        ExecutionService executionService = new ExecutionService();
        CommandLineArguments commandLineArguments = new CommandLineArguments(args);

        try {
            executionService.execute(commandLineArguments);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
