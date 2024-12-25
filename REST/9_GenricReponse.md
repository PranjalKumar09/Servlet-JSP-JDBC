### Objective

We need to modify the existing code so that it responds in a structured JSON format like this:
```json
{
    "status": 200,
    "message": "success",
    "data": [
        "Microsoft",
        "Apple",
        "IBM",
        "TCS"
    ]
}
```
This structure allows the client to easily parse the response and is more maintainable for any further extensions.

To achieve this, we will create three components:
1. **Controller** - This is where we define the endpoints and return the response.
2. **Handler** - This will be a generic handler class to build the response.
3. **Utility** - A utility class to provide reusable functionality.

### Breakdown of Each Component

---

### 1. **Controller**

In the **controller**, we will use the utility function to build the response with the desired structure. Here's how you can modify the existing controller code:

```java
@RestController
public class HomeController {

    // This endpoint returns a generic response
    @GetMapping("/company2")
    public ResponseEntity<?> getCompany2() {
        // The data that you want to send in the response
        List<String> company = Arrays.asList("Microsoft", "Apple", "IBM", "TCS");

        // Use CommonUtil to create the standardized response
        return CommonUtil.createBuildResponse(company, "success", HttpStatus.OK);
    }
}
```

In this updated version, the response will always contain a `status`, `message`, and `data`. The controller remains clean and focused on defining the request handler logic, while the utility class is responsible for standardizing the response.

---

### 2. **Handler**

The **GenericResponseHandler** class is responsible for building the response in the required JSON format. This is where we define the structure of the response, including the `status`, `message`, and `data`. The class uses a `Map` to build the response dynamically.

```java
public class GenericResponseHandler {
    private int status;
    private String message;
    private Object data;

    // Getter and Setter methods for the fields

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // Method to create and return the ResponseEntity
    public ResponseEntity<?> create() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);

        // Return a ResponseEntity with the appropriate HTTP status
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }
}
```

This handler class uses `status`, `message`, and `data` fields to build the response in the correct JSON format. The `create()` method constructs the response using a `LinkedHashMap` to store the data, and it then returns a `ResponseEntity` with the appropriate HTTP status.

---

### 3. **Utility**

The **CommonUtil** class is a utility that helps build the response in a reusable way. It simplifies the code in the controller and ensures that every response is standardized.

```java
public class CommonUtil {

    // Utility method to build the response using the GenericResponseHandler
    public static ResponseEntity<?> createBuildResponse(Object object, String message, HttpStatus status) {
        // Create a new instance of the GenericResponseHandler
        GenericResponseHandler handler = new GenericResponseHandler();
        
        // Set the status, message, and data
        handler.setStatus(status.value());
        handler.setMessage(message);
        handler.setData(object);
        
        // Call the handler's create method to build and return the response
        return handler.create();
    }
}
```

The `createBuildResponse()` method takes three parameters: the data to be returned, a message, and the HTTP status. It then creates a `GenericResponseHandler` instance, sets the fields, and returns the final response by calling the `create()` method on the handler.

---

### Example Response

If you call the `/company2` endpoint, the response would look like this:

```json
{
    "status": 200,
    "message": "success",
    "data": [
        "Microsoft",
        "Apple",
        "IBM",
        "TCS"
    ]
}
```