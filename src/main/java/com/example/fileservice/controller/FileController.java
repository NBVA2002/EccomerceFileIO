package com.example.fileservice.controller;

import com.example.fileservice.dto.ResponeFile;
import com.example.fileservice.service.FileStorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

  private final FileStorageService fileStorageService;

  @Autowired
  private MinioClient minioClient;

  @PostMapping(value = "/{path}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<List<ResponeFile>> uploadFile(@PathVariable("path") String path,
      @RequestPart("file") List<MultipartFile> files) {
    List<ResponeFile> generateFileName = fileStorageService.storeFile(path, files);
    return ResponseEntity.ok(generateFileName);
  }

  @GetMapping("/{path}/{fileName:.+}")
  public ResponseEntity<InputStreamResource> readDetailFile(@PathVariable("path") String path,
      @PathVariable String fileName) {
    return ResponseEntity
        .ok()
        .contentType(MediaType.IMAGE_JPEG)
        .body(fileStorageService.readFileContent(path, fileName));
  }

  @DeleteMapping("/{path}/{fileName:.+}")
  public void deletelFile(@PathVariable("path") String path, @PathVariable String fileName) {
    fileStorageService.deleteFile(path, fileName);
  }


  @PostMapping("/getminio/{path}/{fileName:.+}")
  public ResponseEntity<InputStreamResource> getMinIo(@PathVariable("path") String path,
      @PathVariable String fileName) {
    try {
      return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(new InputStreamResource(
              minioClient.getObject(GetObjectArgs
                  .builder()
                  .bucket("eccomerce")
                  .object("3fb459e3449905545701b418e8220334_tn&quot.png")
                  .build())));
    } catch (ErrorResponseException e) {
      throw new RuntimeException(e);
    } catch (InsufficientDataException e) {
      throw new RuntimeException(e);
    } catch (InternalException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    } catch (InvalidResponseException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (ServerException e) {
      throw new RuntimeException(e);
    } catch (XmlParserException e) {
      throw new RuntimeException(e);
    }

  }

}
