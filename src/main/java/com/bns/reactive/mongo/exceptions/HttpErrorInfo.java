package com.bns.reactive.mongo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorInfo {

    public ZonedDateTime timeStamp;

    public String path;

    public HttpStatus httpStatus;

    public String message;

}
