
File Upload Advanced 

file upload complete with logging -> 

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


we are first checking that file is empty or not, then whehter it is of correct type then
    we saving the Product
    then saving image
    then setting up the imageName of Product then saving it again

    above all three in try and have catch for all in single code block


Here we also notice that logging actually these all message so up in terminal , with timestamp

Uploading the file by 2 ways -> 
    ->   by returning boolean response
    -> or by returning string 

    in string response apart from all boolean thing we also handle what if 2 file have same name then we simply attach the random string in it then save


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





here are extension function used-> 
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

    