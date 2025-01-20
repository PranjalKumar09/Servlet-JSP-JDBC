package com.web.service;

import com.web.model.ProductDtls;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements ExcelService{
    @Override
    public List<ProductDtls> importExcel(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please Import File");
        }
        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Invalid Excel Format");
        }

        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);


        List<ProductDtls> productDtlsList = new ArrayList<>();
        for (int i = 1; i <=sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            String category = row.getCell(0).getStringCellValue();
            String productName = row.getCell(1).getStringCellValue();
            Integer quantity = (int)row.getCell(2).getNumericCellValue();
            double price = row.getCell(3).getNumericCellValue();
            double totalPrice = row.getCell(4).getNumericCellValue();

            ProductDtls productDtls = new ProductDtls();
            productDtls.setCategory(category);
            productDtls.setProductName(productName);
            productDtls.setQuantity(quantity);
            productDtls.setPrice(price);
            productDtlsList.add(productDtls);
        }
        workbook.close();

        return productDtlsList;

    }
}
