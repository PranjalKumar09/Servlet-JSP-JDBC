package com.file.service.impl;

import com.file.model.Product;
import com.file.repository.ProductRepository;
import com.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    public ProductRepository productRepository;


    @Value("${file.upload.path}")
    private String uploadPath;


    @Override
    public byte[] downloadFile(String fileName) {
        String fullPath = uploadPath + File.separator + fileName;
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + fullPath);
        }

        try (InputStream inputStream = new FileInputStream(file)) {
            return StreamUtils.copyToByteArray(inputStream); // Convert InputStream to byte array
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fullPath, e);
        }
    }

    @Override
    public Boolean saveProduct(Product product) {
        Product product1 =   productRepository.save(product);
        return  product1 == null? false:true;
    }
    public static String removeExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return fileName; // No extension found, return the original file name
        }
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    // Get extension from the file name
    public static String getExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return ""; // No extension found, return empty string
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    @Override
    public String uploadFileWithData(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File savefile = new File(uploadPath);

        String rndString = UUID.randomUUID().toString();
        // my_photo.jpeg -> my_photo_jhsfhjkbsf.jpeg -> my_photo.jpeg
        String removeExtension = removeExtension(fileName); // -> my_photo
        String extension = getExtension(fileName);
        fileName =removeExtension+"_"+rndString+"."+extension;


        if (!savefile.exists()) {
            savefile.mkdir();
        }
        String storePath = uploadPath.concat(fileName);

        long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
        if (upload != 0) {
            return fileName;
        }
        return null;
    }

    @Override
    public Boolean uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        // Ensure the upload directory exists
        File saveDir = new File(uploadPath);
        if (!saveDir.isAbsolute()) {
            saveDir = new File(System.getProperty("user.dir"), uploadPath);
        }

        if (!saveDir.exists() && !saveDir.mkdirs()) {
            throw new RuntimeException("Failed to create upload directory: " + saveDir.getAbsolutePath());
        }

        // Build the full file path
        String filePath = saveDir.getAbsolutePath() + File.separator + fileName;

        try {
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile); // Save the file
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Log exception
            return false;
        }
    }

}

