package com.boot.demo.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by baomingli on 2018/8/15.
 */
public class IOUtils {
    private static final Set<PosixFilePermission> PERMS = new HashSet<>();

    static {
        PERMS.add(PosixFilePermission.OWNER_READ);
        PERMS.add(PosixFilePermission.OWNER_WRITE);
        PERMS.add(PosixFilePermission.OWNER_EXECUTE);

        PERMS.add(PosixFilePermission.GROUP_READ);
        PERMS.add(PosixFilePermission.GROUP_EXECUTE);

        PERMS.add(PosixFilePermission.OTHERS_READ);
        PERMS.add(PosixFilePermission.OTHERS_EXECUTE);

    }

    // TODO @baomingli
    public static void saveFile(File file, String content) throws IOException {
        saveFile(file, content, "UTF-8");
    }

    public static void saveFile(File file, String content, String charsetName) throws IOException {
        createDir(file.getParentFile());
        FileUtils.writeByteArrayToFile(file, content.getBytes(charsetName));
        try {
            Files.setPosixFilePermissions(file.toPath(), PERMS);
        } catch (UnsupportedOperationException e) {

        }
    }


    private static void createDir(File file) throws IOException {
        if (file.exists()) {
            return;
        }
        createDir(file.getParentFile());
        boolean result = file.mkdir();
        if (result) {
            try {
                Files.setPosixFilePermissions(file.toPath(), PERMS);
            } catch (UnsupportedOperationException e) {

            }
        }
    }


    public static String getFileContent(String filePath, String charsetName) throws IOException {
        File file = new File(filePath);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return new String(bytes, charsetName);
    }

    public static String getFileContent(String filePath) throws IOException {

        return getFileContent(filePath, "UTF-8");
    }
}
