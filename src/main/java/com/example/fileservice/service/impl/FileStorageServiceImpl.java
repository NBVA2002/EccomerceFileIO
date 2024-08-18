package com.example.fileservice.service.impl;

import com.example.fileservice.dto.ResponeFile;
import com.example.fileservice.model.enumerated.FileType;
import com.example.fileservice.service.FileStorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  private static final long DEFAULT_PART_SIZE = 5 * 1024 * 1024; // 5MB

  private Path storageFolder = Paths.get("upload");
  @Autowired
  private MinioClient minioClient;

  Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);

  @Override
  public List<ResponeFile> storeFile(String path, List<MultipartFile> file) {
    file.stream().forEach(f -> {
      if (f.isEmpty()) {
        throw new RuntimeException("Failed to store empty file");
      }
    });
    List<ResponeFile> responeFiles = new ArrayList<>();

    List<String> fileExtensions = file.stream()
        .map(f -> FilenameUtils.getExtension(f.getOriginalFilename())).toList();

    List<String> generatedFileName = fileExtensions.stream().map(
        f -> UUID.randomUUID().toString().replace("-", "") + "." + f
    ).toList();

      for (int i = 0; i < file.size(); i++) {
        try (InputStream inputStream = file.get(i).getInputStream()) {
          try {
            minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(path)
                .object(generatedFileName.get(i))
                .stream(inputStream, -1, DEFAULT_PART_SIZE)
                .build());
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
          } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
          } catch (ServerException e) {
            throw new RuntimeException(e);
          } catch (XmlParserException e) {
            throw new RuntimeException(e);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

    for (int i = 0; i < file.size(); i++) {
      if (fileExtensions.get(i).equals("mp4") ||
          fileExtensions.get(i).equals("webm") ||
          fileExtensions.get(i).equals("mkv") ||
          fileExtensions.get(i).equals("ogg")) {
        responeFiles.add(new ResponeFile(generatedFileName.get(i), FileType.VIDEO));
      } else {
        responeFiles.add(new ResponeFile(generatedFileName.get(i), FileType.IMAGE));
      }
    }
    return responeFiles;
  }

  @Override
  public Stream<Path> loadAll() {
    return null;
  }

  @Override
  public InputStreamResource readFileContent(String path, String fileName) {
    try {
      return new InputStreamResource(
          minioClient.getObject(GetObjectArgs
              .builder()
              .bucket(path)
              .object(fileName)
              .build()));
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

  @Override
  public void deleteFile(String path, String fileName) {
    RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
        .bucket(path)
        .object(fileName)
        .build();
    try {
      minioClient.removeObject(removeObjectArgs);
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
