package com.global.accelerex.dto.response;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private String code;
    private String description;
    private List<String> errors;

    //

    public ApiError() {
        super();
    }

    public ApiError(final String code, final String description, final List<String> errors) {
        super();
        this.code = code;
        this.description = description;
        this.errors = errors;
    }

    public ApiError(final String code, final String description, final String error) {
        super();
        this.code = code;
        this.description = description;
        errors = Arrays.asList(error);
    }

    //

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}