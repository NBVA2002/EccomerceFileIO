package com.example.fileservice.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {

  @Value("${minio.endpoint}")
  private String endPoint;

  @Value("${minio.username}")
  private String username;

  @Value("${minio.password}")
  private String password;

  @Bean
  public MinioClient minIO() {
    return MinioClient.builder()
        .endpoint(endPoint)
        .credentials(username, password)
        .build();
  }
}
