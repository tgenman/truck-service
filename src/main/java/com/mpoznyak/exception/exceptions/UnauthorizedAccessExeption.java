package com.mpoznyak.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 19:18
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User is not authorized for the resource")
public class UnauthorizedAccessExeption extends RuntimeException {
}
