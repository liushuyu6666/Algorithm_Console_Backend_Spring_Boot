package com.jays.demo.Image;

import java.util.Date;

public class ImageResponse {
    private String imageId;

    private String username;

    private String filename;

    private String key;

    private boolean isPublic;

    private Date uploadDate;

    private String contentType;

    public ImageResponse(String imageId, String username, String filename, String key, boolean isPublic, Date uploadDate, String contentType) {
        this.imageId = imageId;
        this.username = username;
        this.filename = filename;
        this.key = key;
        this.isPublic = isPublic;
        this.uploadDate = uploadDate;
        this.contentType = contentType;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
