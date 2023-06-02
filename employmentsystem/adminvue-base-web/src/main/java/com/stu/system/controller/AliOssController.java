package com.stu.system.controller;

import com.stu.response.Result;
import com.stu.system.service.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api
@RestController
@CrossOrigin
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @ApiOperation(value = "")
    @PostMapping("/uploadImgFile")
    public Result uploadImgFile(MultipartFile file){
        String s = aliOssService.upload(file);
        return Result.ok().data("url",s);
    }

    @ApiOperation(value = "")
    @PostMapping("/deleteImgFile")
    public Result deleteImgFile(String file){
        try {
            String[] split = file.split(".com/");
            System.out.println(split[1]);
            aliOssService.deleteFile(split[1]);
            return Result.ok();
        }catch (Exception e){
            //需要打印错误日志到本地系统中(服务器系统)
            return Result.error();
        }
    }


}
