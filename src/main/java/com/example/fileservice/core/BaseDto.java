package com.example.fileservice.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private Long createdBy;
    private LocalDateTime createdDate;
    private Long lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
