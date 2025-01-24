package com.me.util;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ResourceUtil;
import com.me.config.SuccessResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(tags = "一些工具接口")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CommonUtil {
    @Value("${spring.web.resources.static-locations}")
    String imgDir;

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public SuccessResponse<?> uploadImage(@RequestParam("file") MultipartFile file) {

        // 处理上传的图片逻辑
        if (!file.isEmpty()) {
            try {

                File rootDirectory = new File("");
                String rootPath = rootDirectory.getAbsolutePath();
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取文件的字节数组
                byte[] bytes = file.getBytes();
                // 在这里可以将字节数组保存到数据库或者文件系统中
                // ...

                FileWriter fileWriter = new FileWriter(ResourceUtil.getResourceObj(imgDir).getUrl().getPath() + "\\" + fileName);
                fileWriter.write(bytes, 0, bytes.length);

                return new SuccessResponse<>(fileName, "上传成功。");
            } catch (Exception e) {
                return new SuccessResponse<>(false, "上传失败：" + e.getMessage());
            }
        } else {
            return new SuccessResponse<>(false, "上传失败：文件为空");
        }
    }
}
