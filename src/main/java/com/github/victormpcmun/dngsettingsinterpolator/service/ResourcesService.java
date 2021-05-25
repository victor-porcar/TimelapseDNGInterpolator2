package com.github.victormpcmun.dngsettingsinterpolator.service;

import com.github.victormpcmun.dngsettingsinterpolator.exception.ExecutionException;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ResourcesService {

    public static final ResourcesService INSTANCE = new ResourcesService();

    public List<String> getResourcesFileAsListOfLines(String fileName) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            File file = new File(resource.toURI());
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            return lines;

        } catch (Exception e) {
            throw new ExecutionException("Can not read Resources file " + fileName, e);
        }
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
