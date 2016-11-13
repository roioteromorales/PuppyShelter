package com.roisoftstudio.dognity.domain.dognity.images.cloudinary;

import com.cloudinary.Cloudinary;
import com.roisoftstudio.dognity.domain.dognity.images.UploadCallback;
import com.roisoftstudio.dognity.domain.dognity.images.UploadPhotoTask;

import java.io.InputStream;

import javax.inject.Inject;


public class CloudinaryServiceImpl implements CloudinaryService{

    private final Cloudinary cloudinary;

    @Inject
    public CloudinaryServiceImpl(CloudinaryConfig cloudinaryConfig) {
        cloudinary = cloudinaryConfig.getCloudinary();
    }

    @Override
    public void upload(InputStream photo, UploadCallback callback) {
        new UploadPhotoTask(cloudinary.uploader(), photo, callback).execute();
    }
}
