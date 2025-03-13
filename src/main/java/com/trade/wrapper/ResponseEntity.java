package com.trade.wrapper;

public class ResponseEntity<T> {

    private int statusCode;  // HTTP Status Code
    private String message;   // Message to provide additional information
    private T body;           // Response Body

    // Constructor
    public ResponseEntity(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }

    // Static factory methods for convenience
    public static <T> ResponseEntity<T> success(T body) {
        return new ResponseEntity<>(200, "Success", body);
    }

    public static <T> ResponseEntity<T> created(T body) {
        return new ResponseEntity<>(201, "Resource Created", body);
    }

    public static <T> ResponseEntity<T> notFound(String message) {
        return new ResponseEntity<>(404, message, null);
    }

    public static <T> ResponseEntity<T> internalServerError(String message) {
        return new ResponseEntity<>(500, message, null);
    }

    // Getters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    // Optional: You can add more methods or fields as needed.
}
