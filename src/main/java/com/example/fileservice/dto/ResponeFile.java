package com.example.fileservice.dto;

import com.example.fileservice.model.enumerated.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponeFile {
    private String fileName;
    private FileType type;
}
