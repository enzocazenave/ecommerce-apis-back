package com.apis.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La imagen no existe")
public class ProductImagesNonexistentException extends Exception {
    
}
