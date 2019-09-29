package com.xiaoniucr.controller;

import com.xiaoniucr.utils.ZipHelp;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

/**
 * @author yanghl
 * @date 2019/9/29 15:00
 */
@Controller
public class FileController {

    @RequestMapping("/upload.html")
    public String upload(){
        return "upload";
    }


    @RequestMapping(value = "/fileUplaod",method= RequestMethod.POST)
    @ResponseBody
    public String fileUplaod(HttpServletRequest request, HttpServletResponse response){

        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 通过表单中的参数名来接收文件流（可用 file.getInputStream() 来接收输入流）
            List<MultipartFile> files = multipartRequest.getFiles("file");
            // 获取上传路径
            String uploadPath = this.getClass().getClassLoader().getResource("upload").getPath();
            if(files.size()>0){
                List<String> fileList = new ArrayList<>();
                for(MultipartFile file : files){
                    //获取文件后缀
                    String originalFileName = file.getOriginalFilename();
                    String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
                    //使用uuid作为新的文件名
                    String uuid = UUID.randomUUID().toString().replace("-","");
                    String filePath = uploadPath + "\\" + uuid + fileSuffix;
                    // 创建文件实例
                    File uploadFile = new File(filePath);
                    // 利于spring中的FileCopyUtils.copy()将文件复制
                    try {
                        FileCopyUtils.copy(file.getBytes(), uploadFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fileList.add(filePath);
                }
                //打包压缩成zip
                String zipName = UUID.randomUUID().toString().replace("-","")+".zip";
                File fileZip = new File(uploadPath + "\\" +zipName);
                FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(fileZip);
                    ZipOutputStream toClient = new ZipOutputStream(outStream);
                    ZipHelp.zipFile(fileList,toClient);
                    toClient.close();
                    outStream.close();
                    ZipHelp.downloadFile(fileZip,response,false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "OK";
        } else {
            return "不是 MultipartHttpServletRequest";
        }

    }


    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletResponse response){
        List<String> fileList = new ArrayList<>();
        fileList.add("D:\\IdeaProjects\\odoq\\target\\classes\\upload\\002c3c3fc68f425e972c08c009c3713a.jpg");
        //打包压缩成zip
        String uploadPath = this.getClass().getClassLoader().getResource("upload").getPath();
        String zipName = UUID.randomUUID().toString().replace("-","")+".zip";
        File fileZip = new File(uploadPath + "\\" +zipName);
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(fileZip);
            ZipOutputStream toClient = new ZipOutputStream(outStream);
            ZipHelp.zipFile(fileList,toClient);
            toClient.close();
            outStream.close();
            ZipHelp.downloadFile(fileZip,response,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
