package com.montani.exam.helper;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class FileHelper {

    public static void saveImage(long bookId, MultipartFile file) throws IOException {
        String uploadDir= "covers";
        saveFile(uploadDir, String.format("%d-%s", bookId, file.getOriginalFilename()), file);
    }

    /**
     *
     * @param bookId
     * @param filename
     * @return
     */
    public static String getImageAsBase64(long bookId, String filename) {
        String uploadDir = "covers";
        String filePath = String.format("%s/%d-%s", uploadDir, bookId, filename);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            return encodedString;
        }catch (Exception e) {
            return "";
        }
    }

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
