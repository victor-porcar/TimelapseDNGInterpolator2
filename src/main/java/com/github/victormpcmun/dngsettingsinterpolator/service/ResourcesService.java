package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ResourcesService {

    public static final ResourcesService INSTANCE = new ResourcesService();

    public List<String> getResourcesFileAsListOfLines(String fileName) {

        List<String> result = new ArrayList<>();

        try (
            InputStream inputStream = this.getClass().getResourceAsStream("/" + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Stream<String> lines = bufferedReader.lines();
        ) {
            lines.forEach(result::add);
        } catch (Exception e) {
            throw new ExecutionException("Can not read Resources file " + fileName, e);
        }
        return result;

    }



    public String getResourcesFile(String fileName) {
        List<String> listOfLines = getResourcesFileAsListOfLines(fileName);
        StringBuilder sb = new StringBuilder();
        for (String line: listOfLines) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }

}
