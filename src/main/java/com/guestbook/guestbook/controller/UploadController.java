package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@Log4j2
public class UploadController {

    @Value("${study.file.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            String originalName = uploadFile.getOriginalFilename();
            log.info("originalName : " + originalName);
            // String fileName = originalName.substring(originalName.lastIndexOf("\\" + 1)); //IE
            String fileName = originalName; // chrome
            log.info("fileName : " + fileName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // 파일명 uuid 추가
            String uuid = UUID.randomUUID().toString();
            String fileFullPath = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            log.info("fileFullPath : " + fileFullPath);

            Path filePath = Paths.get(fileFullPath);
            log.info("filePath : " + filePath);

            try {
                // 원본 파일 저장
                uploadFile.transferTo(filePath);

                generateThumbnail(fileName, folderPath, uuid, filePath);

                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("srcFileName : " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file : " + file);

            HttpHeaders header = new HttpHeaders();

            //MIME 타입 처리 : 파일 확장자에 따라 브라우저에 다르게 전송
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            // 원본 파일 삭제
            File file = new File(uploadPath+ File.separator + srcFileName);
            boolean result = file.delete();

            // 썸네일 삭제
            File thumbnail = new File(file.getParent(), "thumb_"+ file.getName());
            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   private String makeFolder() {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = dateStr.replace("//", File.separator);
        log.info("folderPath : " + folderPath);

        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;

    }

    private void generateThumbnail(String fileName, String folderPath, String uuid, Path filePath) throws IOException {
        // 썸네일 생성
        String thumbnailName = uploadPath + File.separator + folderPath + File.separator;
        thumbnailName += "thumb_" + uuid + "_" + fileName;

        File thumbnailFile = new File(thumbnailName);
        // 썸네일 생성
        Thumbnailator.createThumbnail(filePath.toFile(), thumbnailFile, 100, 100);
    }
}
