package com.alevel.java.nix.ionio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilesHandlerTest {

    private final String[] filesInNioTestDir = {
            "file1.txt",
            "file2.txt",
            "second_level"
    };

    private final String[] filesInSecondLevel = {
            "file3.txt",
            "file4.txt",
            "third_level"
    };

    private final String[] filesInNioTestDirWithSame = {
            "file1.txt",
            "file1(1).txt",
            "file1(2).txt",
            "file2.txt",
            "file2(1).txt",
            "file2(2).txt",
            "second_level"
    };

    private final String[] filesInSecondDirWithSame = {
            "file3.txt",
            "file3(1).txt",
            "file3(2).txt",
            "file4.txt",
            "file4(1).txt",
            "file4(2).txt",
            "third_level"
    };

    @BeforeEach
    void setUp() throws IOException {
        deleteDir("./nioTestDir");
        deleteDir("./nioTestDestDir");

        File fileDir = new File("./nioTestDir");
        File secondLevelDir = new File("./nioTestDir/second_level");
        File thirdLevelDir = new File("./nioTestDir/second_level/third_level");


        if (!fileDir.exists()) {
            if (fileDir.mkdir()) {
                System.out.println("nioTestDir directory was created");
            } else {
                throw new IOException("Cannot create nioTestDir");
            }
        }
        Files.createFile(Paths.get("./nioTestDir/file1.txt"));
        Files.createFile(Paths.get("./nioTestDir/file2.txt"));

        if (!secondLevelDir.mkdir()) {
            throw new IOException("Cannot create 'second_level' directory");
        }
        Files.createFile(Paths.get("./nioTestDir/second_level/file3.txt"));
        Files.createFile(Paths.get("./nioTestDir/second_level/file4.txt"));

        if (!thirdLevelDir.mkdir()) {
            throw new IOException("Cannot create 'third_level' directory");
        }
    }

    private void deleteDir(String firstDir) throws IOException {
        File fileDir = new File(firstDir);
        File secondLevelDir = new File(firstDir + "/second_level");
        File thirdLevelDir = new File(firstDir + "/second_level/third_level");

        if (fileDir.exists()) {
            thirdLevelDir.delete();
            String[] filesToDelete = secondLevelDir.list();
            if (filesToDelete != null) {
                for (String fName : filesToDelete) {
                    Files.deleteIfExists(Paths.get(secondLevelDir + "/" + fName));
                }
            }
            secondLevelDir.delete();

            filesToDelete = fileDir.list();
            if (filesToDelete != null) {
                for (String fName : filesToDelete) {
                    Files.deleteIfExists(Paths.get(firstDir + "/" + fName));
                }
            }

            if (fileDir.delete()) {
                System.out.println("nioTestDir directory was deleted");
            } else {
                System.out.println("Error to delete nioTestDir");
            }
        }
    }

    private void assertCopy(String[] list1, String[] list2, int countCopies) throws IOException {
        FilesHandler filesHandler = new FilesHandler();

        for (int i = 0; i < countCopies; i++) {
            filesHandler.copyFilesTo("./nioTestDir", "./nioTestDestDir");
        }

        File nioTestDestDir = new File("./nioTestDestDir");

        if (nioTestDestDir.exists()) {
            if (nioTestDestDir.delete()) {
                System.out.println("nioTestDestDir was deleted");
            }
        }

        assertTrue(nioTestDestDir.exists());
        assertTrue(nioTestDestDir.isDirectory());

        String[] filesNamesList = nioTestDestDir.list();
        assertNotNull(filesNamesList);

        Arrays.sort(list1);
        Arrays.sort(filesNamesList);

        assertArrayEquals(filesNamesList, list1);

        File secondLevelDir = new File("./nioTestDestDir/second_level");
        assertTrue(secondLevelDir.exists());
        assertTrue(secondLevelDir.isDirectory());

        filesNamesList = secondLevelDir.list();
        assertNotNull(filesNamesList);

        Arrays.sort(filesNamesList);
        Arrays.sort(list2);

        assertArrayEquals(filesNamesList, list2);

        File thirdLevelDir = new File("./nioTestDestDir/second_level/third_level");
        assertTrue(thirdLevelDir.exists());
        assertTrue(thirdLevelDir.isDirectory());
    }

    @Test
    void copyFilesTo() throws IOException {
        System.out.println("First test");
        assertCopy(filesInNioTestDir, filesInSecondLevel, 1);
    }

    @Test
    void copyFilesWithSameExisted() throws IOException {
        System.out.println("Second test");
        assertCopy(filesInNioTestDirWithSame, filesInSecondDirWithSame, 3);
    }

}