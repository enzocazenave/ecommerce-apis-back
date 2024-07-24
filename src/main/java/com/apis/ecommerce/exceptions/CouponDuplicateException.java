package com.apis.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El cupón que se intenta crear ya existe")
public class CouponDuplicateException extends Exception {
    
}
