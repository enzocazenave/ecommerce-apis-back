package com.apis.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "No se ingreso un valor válido para el precio y/o cantidad del producto.")
public class InvalidPriceOrUnitProductException extends Throwable {
}
