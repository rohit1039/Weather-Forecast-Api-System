package io.swagger.api;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-19T13:27:40.311592936Z[GMT]")
public class ApiException extends Exception {
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
