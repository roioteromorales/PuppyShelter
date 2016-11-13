package com.roisoftstudio.dognity.domain.dognity.images.cloudinary;

import com.roisoftstudio.dognity.domain.dognity.images.UploadCallback;

import java.io.InputStream;

public interface CloudinaryService {
    void upload(InputStream photo, UploadCallback callback);
}
