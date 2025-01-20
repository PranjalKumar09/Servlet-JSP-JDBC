package com.web.service;

import com.web.model.ProductDtls;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ExcelService {
    List<ProductDtls> importExcel(MultipartFile file) throws IOException;
}
