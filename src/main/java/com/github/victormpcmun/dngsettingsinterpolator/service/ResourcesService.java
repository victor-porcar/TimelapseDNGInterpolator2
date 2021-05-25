package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ResourcesService {

    public static final ResourcesService INSTANCE = new ResourcesService();

    public List<String> getResourcesFileAsListOfLines(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new ExecutionException("file not found! " + fileName);
        }

        File file;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new ExecutionException("Can not read Resources file " + fileName, e);
        }

        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ExecutionException("Can not read Resources file " + fileName, e);
        }
        return lines;
    }

    public String getResourcesFile(String fileName) {
        List<String> listOfLines = getResourcesFileAsListOfLines(fileName);
        StringBuilder sb = new StringBuilder();
        for (String line: listOfLines) {
            sb.append(line+System.lineSeparator());
        }
        return sb.toString();
    }

}
