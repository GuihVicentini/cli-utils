package com.gvb.dupliwise.commands;

import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    @CommandLine.Parameters(arity = "0..1", description = "The absolute path to the directory to be used")
    private String dirPath;

    @CommandLine.Option(names = {"-H", "--hidden"}, description = "show hidden files")
    private boolean includeHiddenFiles;

    @Override
    public void run() {
        String message = "Finding duplicated files in Directory: %s";
        String currentDir = System.getProperty("user.dir");

        if (includeHiddenFiles) {
            System.out.println("Including hidden files");
        }

        if (dirPath != null) {
            System.out.printf((message) + "%n", dirPath);
            listDuplicates(dirPath, includeHiddenFiles);
            return;
        }

        System.out.printf((message) + "%n", currentDir);
        listDuplicates(currentDir, includeHiddenFiles);
    }

    private void listDuplicates(String directoryName, boolean isHidden) {
        findDuplicates(directoryName, isHidden).forEach((fileHash, files) -> {

            if (files.size() <= 1) {
                return;
            }

            System.out.printf("File hash %s%n", fileHash);
            files.forEach(file -> System.out.println(file.getAbsolutePath()));
        });
    }

    private Map<String, List<File>> findDuplicates(String directoryName, boolean isHidden) {
        File dir = new File(directoryName);
        Map<String, List<File>> fileMap = new HashMap<>();

        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && (file.isHidden() == isHidden)) {
                    String fileContentHash = calculateSHA256(file);

                    List<File> fileList = fileMap.getOrDefault(fileContentHash, new ArrayList<>());
                    fileList.add(file);
                    fileMap.put(fileContentHash, fileList);
                }
            }
        }

        return fileMap;
    }


    private String calculateSHA256(File file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int bytesRead;

            try (FileInputStream fis = new FileInputStream(file)) {
                while ((bytesRead = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }

            byte[] hash = digest.digest();
            return bytesToHex(hash);

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
