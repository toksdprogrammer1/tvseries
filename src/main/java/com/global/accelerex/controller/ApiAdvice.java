package com.global.accelerex.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.global.accelerex.exception.*;
import com.global.accelerex.dto.response.BaseResponse;
import com.global.accelerex.exception.Error;
import com.global.accelerex.utils.EnumUtil;
import com.global.accelerex.utils.ExceptionUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



/**
 * Created by tokunbo.ojo on 06/24/2019
 */
@SuppressWarnings (value="unchecked")
@ControllerAdvice(annotations = {RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ApiAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
       // logger.error("{}", e.getLocalizedMessage());
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.toString());
        response.setDescription(messageSource.getMessage("request.invalid", null, Locale.ENGLISH));
        Throwable cause = e.getMostSpecificCause();
        List<Error> errors = new ArrayList<>();
        if (cause instanceof InvalidFormatException ||
                cause instanceof InvalidEnumException) {
            Throwable t = ExceptionUtil.get(e, JsonMappingException.class);
            if (t != null) {
                List<JsonMappingException.Reference> reference = ((JsonMappingException) t).getPath();
                StringBuilder builder = new StringBuilder(reference.get(0).getFieldName());
                if (reference.size() > 1) {
                    reference
                            .subList(1, reference.size())
                            .forEach(r -> {
                                builder.append("[");
                                if (StringUtils.isNotBlank(r.getFieldName())){
                                    builder.append(r.getFieldName());
                                }});
                    builder.append(StringUtils.repeat("]", reference.size() - 1));
                }
                String field = builder.toString();
                Object value = cause instanceof InvalidFormatException ?
                        ((InvalidFormatException) cause).getValue() :
                        ((InvalidEnumException) cause).getRejected();
                Class type = cause instanceof InvalidFormatException ?
                        ((InvalidFormatException) cause).getTargetType() :
                        ((InvalidEnumException) cause).getEnumClass();
                String message;
                if (type.isEnum()) {
                    message = messageSource.getMessage("typeMismatch.java.lang.Enum",
                            new Object[]{value, type.getSimpleName(),
                                    StringUtils.join(EnumUtil.getEnumConstants(type), ", ")}, Locale.ENGLISH);
                } else {
                    message = messageSource.getMessage("typeMismatch", new Object[]{value}, Locale.ENGLISH);
                }
                errors.add(new Error(field, message));
            }
        } else if (cause instanceof PropertyBindingException) {
            PropertyBindingException ex = (PropertyBindingException) cause;
            String field = ex.getPropertyName();
            String expected = StringUtils.join(ex.getKnownPropertyIds(), ", ");
            String error = messageSource.getMessage("field.invalid", new Object[]{expected}, Locale.ENGLISH);
            errors.add(new Error(field, error));
        }
        response.setErrors(errors);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, RequestNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleValidationException(Exception e) {
        //logger.error("{}",e.getLocalizedMessage());
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.toString());
        response.setDescription(messageSource.getMessage("request.invalid", null, Locale.ENGLISH));
        BindingResult result;
        if (e instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else {
            result = ((RequestNotValidException) e).getBindingResult();
        }
        List<Error> errors = new ArrayList<>();
        result.getGlobalErrors()
                .forEach(error ->
                        errors.add(new Error(error.getObjectName(), messageSource.getMessage(error, Locale.ENGLISH))));
        result.getFieldErrors()
                .forEach(
                        error -> {
                            FieldError resolvable = error;
                            if (error.isBindingFailure()) {
                                String[] codes = error.getCodes();
                                Object[] args = new Object[3];
                                args[0] = error.getRejectedValue();
                                Class type = result.getFieldType(error.getField());
                                if (type != null && type.isEnum()) {
                                    codes = new String[]{"typeMismatch.java.lang.Enum"};
                                    args[1] = type.getSimpleName();
                                    args[2] = StringUtils.join(EnumUtil.getEnumConstants(type), ", ");
                                }
                                resolvable = new FieldError(error.getObjectName(), error.getField(),
                                        error.getRejectedValue(), error.isBindingFailure(),
                                        codes, args, error.getDefaultMessage());
                            }
                            errors.add(new Error(error.getField(), messageSource.getMessage(resolvable, Locale.ENGLISH)));
                        }
                );
        response.setErrors(errors);
       // return response;
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BaseResponse> handleConflictException(DuplicateKeyException e) {

        //logger.error("{}", e.getLocalizedMessage());
        String message = e.getMostSpecificCause().getMessage();

        String table = StringUtils.substringBetween(message, "tbl_", "_col");
        String columns = StringUtils.substringBetween(message, "_col_", "'.");

        String resource = table == null ? "resource" : table;
        String fields = columns == null ? "fields" : columns.replaceAll("__", ", ").replaceAll("_", " ");

        BaseResponse response = new BaseResponse();
        response.setCode("40900");
        response.setDescription(messageSource.getMessage("resource.already.exists",
                new Object[]{WordUtils.capitalize(resource.replaceAll("_", " ")), fields}, Locale.ENGLISH));
        //return response;
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse> handleNotFoundException(ResourceNotFoundException e) {


        String message = e.getMessage();
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.NOT_FOUND.toString());
        response.setDescription(message);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleBadRequestException(BadRequestException e) {

        String message = e.getMessage();
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.BAD_REQUEST.toString());
        response.setDescription(message);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BaseResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {

        //logger.error("{}", e.getLocalizedMessage());
        String message = e.getMessage();
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.CONFLICT.toString());
        response.setDescription(message);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


}



