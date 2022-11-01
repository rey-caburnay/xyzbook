package com.montani.exam.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.montani.exam.helper.JsonHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ApiResponse<T> implements Serializable {

    @Schema(description = "http status like 200, 400, 404, 500, etc..", example = "200")
    private int status;

    @Schema(description = "timestamp of execution")
    private Date timestamp;

    @Schema(description= "message from the api", example = "Null Pointer Exception...")
    private String message;

    @Schema(description= "object data for successful operation", example = "{}")
    private T data;

    @JsonIgnore
    public ResponseEntity returnAsResponseEntity() {
        HttpHeaders headers = new HttpHeaders();
        String json = JsonHelper.serializeObject(this);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity(json, headers, HttpStatus.valueOf(this.status));
    }
}
