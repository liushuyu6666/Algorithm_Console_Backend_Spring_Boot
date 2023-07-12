package com.jays.demo.Image;

import com.jays.demo.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.jays.demo.Payload.ResponseBody;


import java.util.List;

@RequestMapping("/v1/jays")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    AuthService authService;

    @PostMapping("/images")
    public ResponseEntity<?> uploadImageToS3(
            @RequestHeader("Authorization") String authentication,
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "name") String imageName,
            @RequestParam(value = "isPublic") boolean isPublic
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);
            String contentType = multipartFile.getContentType();
            assert contentType != null;
            String MIMEType = contentType.split("/")[0];


            if(contentType.length() == 0 || !MIMEType.equals("image")) {
                return ResponseEntity.status(415).body("Unsupported media type. " + contentType + " is not an image");
            }

            this.imageService.uploadImage(imageName, multipartFile, userId, contentType, isPublic);

            return ResponseEntity.ok("Image " + imageName + " uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/images")
    public ResponseEntity<?> listImages(
            @RequestHeader("Authorization") String authentication
    ) {
        try {
            String userId = this.authService.authenticateToken(authentication);
            List<Image> images = this.imageService.listImages(userId);

            ResponseBody<List<Image>> responseBody = new ResponseBody<>(images, "", null);
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/images/public")
    public ResponseEntity<?> listPublicImages(
    ) {
        List<Image> images = this.imageService.listPublicImages();

        ResponseBody<List<Image>> responseBody = new ResponseBody<>(images, "", null);
        return ResponseEntity.ok(responseBody);
    }
}
