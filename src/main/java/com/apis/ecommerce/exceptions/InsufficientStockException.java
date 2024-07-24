package com.apis.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "the product does not have sufficient stock.")
public class InsufficientStockException extends RuntimeException {
}
