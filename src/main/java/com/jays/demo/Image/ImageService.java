package com.jays.demo.Image;

import com.jays.demo.S3.S3Repository;
import com.jays.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    S3Repository s3Repository;

    @Autowired
    UserRepository userRepository;

    public void uploadImage(String imageName, MultipartFile multipartFile, String userId, String contentType, boolean isPublic) throws Exception {
        String imageId = UUID.randomUUID().toString();

        // TODO: prefix images should be configurable
        String key = this.s3Repository.uploadFile(imageId, multipartFile, contentType, "images");

        Image image = new Image(userId, imageName, key, isPublic, contentType);
        this.imageRepository.save(image);
    }

    public List<ImageResponse> listImages(String userId) {
        ImageConverter imageConverter = new ImageConverter(s3Repository, userRepository);

        List<Image> imagesInDatabase = this.imageRepository.findByUserId(userId);
        List<Image> validImagesInS3 = imagesInDatabase.stream().filter(image -> this.s3Repository.objectExists(image.getKey())).toList();
        return validImagesInS3.stream().map(imageConverter::convertImageToImageResponse).toList();
    }

    public List<ImageResponse> listPublicImages() {
        ImageConverter imageConverter = new ImageConverter(s3Repository, userRepository);

        List<Image> imagesInDatabase = this.imageRepository.findByIsPublic(true);
        List<Image> validImagesInS3 = imagesInDatabase.stream().filter(image -> this.s3Repository.objectExists(image.getKey())).toList();
        return validImagesInS3.stream().map(imageConverter::convertImageToImageResponse).toList();
    }
}
