package com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary;

import com.roisoftstudio.puppyshelter.domain.puppies.images.UploadCallback;

import java.io.InputStream;

public interface CloudinaryService {
    void upload(InputStream photo, UploadCallback callback);
}
