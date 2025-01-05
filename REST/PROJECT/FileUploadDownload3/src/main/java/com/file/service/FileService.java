package com.file.service;


import com.file.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {
 Boolean uploadFile(MultipartFile file);
 byte[] downloadFile(String fileName);
 Boolean saveProduct(Product product);
String uploadFileWithData(MultipartFile file) throws IOException;
}
