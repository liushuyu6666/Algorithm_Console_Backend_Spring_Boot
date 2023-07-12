package com.jays.demo.Image;

import com.jays.demo.S3.S3Repository;
import com.jays.demo.User.User;
import com.jays.demo.User.UserRepository;

import java.util.Optional;

public class ImageConverter {
    private final S3Repository s3Repository;
    private final UserRepository userRepository;

    public ImageConverter(S3Repository s3Repository, UserRepository userRepository) {
        this.s3Repository = s3Repository;
        this.userRepository = userRepository;
    }

    public ImageResponse convertImageToImageResponse(Image image) {
        String preSignedUrl = this.s3Repository.generatePreSignedUrl(image.getKey(), image.getContentType()).toString();
        Optional<User> optionalUser = this.userRepository.findByUserId(image.getUserId());
        String username = (optionalUser.isPresent()) ? optionalUser.get().getUsername() : "no user";

        return new ImageResponse(
                image.getImageId(),
                username,
                image.getFileName(),
                preSignedUrl,
                image.isPublic(),
                image.getUploadDate(),
                image.getContentType()
        );
    }
}
