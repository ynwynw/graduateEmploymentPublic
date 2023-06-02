package com.stu.system.service.impl;

import com.stu.system.entity.OssEntity;
import com.stu.system.service.AliOssService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
public class AliOssServiceImpl implements AliOssService, InitializingBean {

    @Autowired
    private OssEntity ossEntity;

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    @Override
    public void afterPropertiesSet() throws Exception {
        endpoint = ossEntity.getEndpoint();
        accessKeyId = ossEntity.getAccessKeyId();
        accessKeySecret = ossEntity.getAccessKeySecret();
        bucketName = ossEntity.getBucketName();
    }

    @Override
    public void createBucket() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (ossClient.doesBucketExist(bucketName)) {
            throw new RuntimeException(bucketName + "在对象存储的Bucket列表中已经存在");
        }
        ossClient.createBucket(bucketName);
        ossClient.shutdown();
    }

    @Override
    public String upload(MultipartFile file) {
        String uploadUrl = null;
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            InputStream inputStream = file.getInputStream();

            String datePath = new DateTime().toString("yyyy/MM/dd");
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            fileName = datePath + "/" + newName;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType(getcontentType(fileType));

            ossClient.putObject(bucketName, fileName, inputStream, objectMetadata);

            ossClient.shutdown();

            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);

            uploadUrl = ossClient.generatePresignedUrl(bucketName, fileName, expiration).toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadUrl.substring(0, uploadUrl.indexOf("?"));
    }

    @Override
    public void download(String fileName) throws IOException {
        String objectName = fileName;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("\n" + line);
            }
            content.close();
        }
        ossClient.shutdown();
    }

    @Override
    public void listFile() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectListing objectListing = ossClient.listObjects(bucketName);
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }

        ossClient.shutdown();
    }

    @Override
    public void deleteFile(String fileName) {
        String objectName = fileName;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.deleteObject(bucketName, objectName);

        ossClient.shutdown();
    }

    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }

}
