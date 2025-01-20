### Reading Excel Database

#### **Dependencies (pom.xml)**
Add the following dependencies to work with Excel files:
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.4.0</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.4.0</version>
</dependency>
```

---

#### **Model: `ProductDtls`**
Represents the structure of the product data:
```java
private Integer id;
private String category;
private String productName;
private Integer quantity;
private Double price;
```

---

#### **Excel File Reading (`EmailServiceImpl`)**
Implements `ExcelService` to read Excel data:
1. **Validations:**
   - Check if the file is empty.
   - Ensure file extension is `.xlsx`.

2. **Process Excel File:**
   - Open file as `InputStream`.
   - Use `XSSFWorkbook` to read data.
   - Parse rows and extract data from cells.

3. **Data Mapping:**
   - Read columns: `Category`, `Product Name`, `Quantity`, `Price`, and `Total Price`.
   - Map extracted data into `ProductDtls` objects.
   - Add objects to a list.

4. **Return Data:**
   - Return the list of `ProductDtls`.

**Code Snippet:**
```java
@Override
public List<ProductDtls> importExcel(MultipartFile file) throws IOException {
    if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
        throw new IllegalArgumentException("Invalid File");
    }

    InputStream inputStream = file.getInputStream();
    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
    XSSFSheet sheet = workbook.getSheetAt(0);

    List<ProductDtls> productDtlsList = new ArrayList<>();
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        XSSFRow row = sheet.getRow(i);
        if (row != null) {
            ProductDtls product = new ProductDtls();
            product.setCategory(row.getCell(0).getStringCellValue());
            product.setProductName(row.getCell(1).getStringCellValue());
            product.setQuantity((int) row.getCell(2).getNumericCellValue());
            product.setPrice(row.getCell(3).getNumericCellValue());
            productDtlsList.add(product);
        }
    }
    workbook.close();
    return productDtlsList;
}
```

---

#### **Product Saving (`ProductServiceImpl`)**
Implements `ProductService` to save product data into the database:
1. **Save Products:**
   - Use `ProductRepo.saveAll()` to persist data.
   - Check if the list is saved successfully.

2. **Return Status:**
   - Return `true` if data is saved, else `false`.

**Code Snippet:**
```java
@Override
public Boolean saveProduct(List<ProductDtls> product) {
    List<ProductDtls> saveAll = productRepo.saveAll(product);
    return !CollectionUtils.isEmpty(saveAll);
}
```

---

### Summary
1. **Dependencies:** Use Apache POI for Excel processing.
2. **Model:** Define `ProductDtls` to map Excel data.
3. **Excel Reading:** Validate, parse, and map Excel data into objects.
4. **Data Saving:** Save mapped objects into the database with `ProductRepo`.