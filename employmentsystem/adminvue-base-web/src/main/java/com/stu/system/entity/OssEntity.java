package com.stu.system.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alioss")
@Component
@Data
public class OssEntity {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;


}
