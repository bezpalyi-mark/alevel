package com.alevel.java.nix.ionio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class FilesHandler {
    public boolean copyFilesTo(String srcDir, String dstDir) throws IOException {
        File srcFile = new File(srcDir);
        File dstFile = new File(dstDir);
        if (!srcFile.exists()) {
            System.out.println("Source folder does not exists!");
            return false;
        }
        if (!dstFile.exists()) {
            if (dstFile.mkdir()) {
                System.out.println(MessageFormat.format("{0} folder was created", dstFile.getName()));
            }
        }
        File[] filesList = srcFile.listFiles();
        if (filesList == null) {
            return true;
        }
        for (File file : filesList) {
            if (file.isDirectory()) {
                copyFilesTo(file.getPath(), dstDir + "/" + file.getName());
            } else {
                try {
                    Files.copy(
                            file.toPath(),
                            Path.of(dstFile.toPath() + "/" + file.getName())
                    );
                } catch (FileAlreadyExistsException e) {
                    int number = 1;
                    while (!tryToCreateSameFile(
                            file.toPath(),
                            Path.of(dstFile.toPath() + "/" + file.getName()),
                            number)) {
                        number++;
                    }

                }
            }
        }
        return true;
    }

    private boolean tryToCreateSameFile(Path srcPath, Path dstPath, int number) throws IOException {
        try {
            List<String> extAndFile = cutExtension(dstPath.toString());
            Files.copy(srcPath, Path.of( extAndFile.get(1) + "(" + number + ")." + extAndFile.get(0)));
        } catch (FileAlreadyExistsException e) {
            return false;
        } catch (IOException e) {
            throw new IOException(e);
        }
        return true;
    }

    private List<String> cutExtension(String fileName) {
        StringBuilder extension = new StringBuilder(fileName.length());
        StringBuilder file = new StringBuilder(fileName.length());
        boolean extensionGet = false;
        for (int i = fileName.length() - 1; i >= 0 ; i--) {
            if (fileName.charAt(i) == '.' && !extensionGet) {
                extensionGet = true;
                continue;
            }
            if (extensionGet) {
                file.append(fileName.charAt(i));
            } else {
                extension.append(fileName.charAt(i));
            }
        }
        extension = extension.reverse();
        file = file.reverse();
        return Arrays.asList(extension.toString(), file.toString());
    }

    public static void main(String[] args) {
        File nioTestDir = new File("./nioTestDir");
        File nioTestDestDir = new File("./nioTestDestDir");
        nioTestDir.mkdir();
        if(!nioTestDir.exists()) {
            System.err.println("Cannot remove nioTestDir");
        }
        if(!nioTestDir.delete()) {
            System.err.println("Cannot remove nioTestDir");
        }
        if(!nioTestDestDir.delete()) {
            System.err.println("Cannot remove nioTestDestDir");
        }
    }
}
