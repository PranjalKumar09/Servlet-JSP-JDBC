package com.file.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.dto.UserRequest;
import com.file.model.Product;
import com.file.service.FileService;
import com.file.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetails() {
        return new ResponseEntity<>( userService.getUserDtls(), HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<Object> index()
    {

        return new ResponseEntity<>( "Application is OK", HttpStatus.OK);
    }


        @PostMapping("/login")
        public ResponseEntity<Object> login(@RequestBody UserRequest userRequest) {
    //        System.out.println(userRequest);
            String token =  userService.login(userRequest);

            if (token==null) {
                return new ResponseEntity<>("Invalid ", HttpStatus.UNAUTHORIZED);
            }
            System.out.println(token);

            return new ResponseEntity<>( token, HttpStatus.OK);
        }



    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        try{
        System.out.println("Submitted");
            Boolean result = fileService.uploadFile(file);

            return result? new ResponseEntity<>("upload success" , HttpStatus.CREATED): new ResponseEntity<>("upload failed" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){

            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/upload-data-Complete")
    public ResponseEntity<?> uploadFileData(@RequestParam String product,
                                                @RequestParam MultipartFile file) {
        List<String> extensionAllowed = Arrays.asList("jpg", "jpeg", "png", "gif");
        if (file.isEmpty())
            return ResponseEntity.badRequest().body("File is empty");
        if (!extensionAllowed.contains(file.getOriginalFilename()))
            return ResponseEntity.badRequest().body("Only JPG , PNG, GIF and JPEG files are supported");

        try {
            // Log incoming data for debugging
            log.info("Product Data: {}", product);
            log.info("File Name: {}", file.getOriginalFilename());

            // Deserialize the product JSON data into a Product object
            ObjectMapper objectMapper = new ObjectMapper();
            Product productDto = objectMapper.readValue(product, Product.class);

            // Save the product data
            Boolean productSaved = fileService.saveProduct(productDto);

            if (!productSaved) {
                return new ResponseEntity<>("Failed to save product", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Handle file upload
            Boolean fileUploaded = fileService.uploadFile(file);

            if (!fileUploaded) {
                return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // After successful file upload, set the file name in the product object
            productDto.setImage(file.getOriginalFilename());

            // Optionally, save the updated product with image file info (if necessary)
            fileService.saveProduct(productDto);

            return new ResponseEntity<>("Upload and save success", HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Error during file upload and product save", e);
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload-data-complete")
    public ResponseEntity<String> uploadDataAndFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("product") String productJson) {
        // Allowed file extensions
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

        // Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }




        // Extract file extension
        String fileName = file.getOriginalFilename();
        String extension = getFileExtension(fileName).toLowerCase();

        // Validate the file extension
        if (!allowedExtensions.contains(extension)) {
            return ResponseEntity.badRequest().body("Only JPG, PNG, GIF, and JPEG files are supported");
        }

        try {
            // Log the incoming data
            log.info("Product Data: {}", productJson);
            log.info("File Name: {}", file.getOriginalFilename());

            // Deserialize the product JSON data into a Product object
            ObjectMapper objectMapper = new ObjectMapper();
            Product productDto = objectMapper.readValue(productJson, Product.class);

            // Save the product data (implement your save logic here)
            Boolean productSaved = fileService.saveProduct(productDto);
            if (!productSaved) {
                return new ResponseEntity<>("Failed to save product", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Handle file upload
            String fileUploadResponse = fileService.uploadFileWithData(file);
            if (fileUploadResponse == null) {
                return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // After successful file upload, set the file name in the product object
            productDto.setImage(fileUploadResponse);

            // Optionally, save the updated product with image file info (if necessary)
            fileService.saveProduct(productDto);

            // Return success message
            return new ResponseEntity<>("Upload and save success", HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Error during file upload and product save", e);
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String fileName) {
        try {
            // Get the file content (byte array) from the service
            byte[] downloadFile = fileService.downloadFile(fileName);

            // If no file found, return 404
            if (downloadFile == null || downloadFile.length == 0) {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

            // Get file extension and map it to content type
//            String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            String fileExtension =getFileExtension(fileName);
            String contentType = getMimeType(fileExtension);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentLength(downloadFile.length);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(downloadFile, headers, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging (optional)
            e.printStackTrace();
            return new ResponseEntity<>("Error downloading file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return ""; // No extension found
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }
    public String getMimeType(String fileType) {
        switch (fileType.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            case "xml":
                return "application/xml";
            case "csv":
                return "text/csv";
            case "zip":
                return "application/zip";
            case "json":
                return "application/json";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls":
                return "application/vnd.ms-excel";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt":
                return "application/vnd.ms-powerpoint";
            case "pptx":
                return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            default:
                return "application/octet-stream"; // Default binary type
        }

    }

   /* @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestParam("file") MultipartFile file, @RequestBody Product productDto) {

    }
*/

}
