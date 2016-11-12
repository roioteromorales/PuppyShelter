package com.roisoftstudio.puppyshelter.domain.puppies.network.cloudinary;

import com.cloudinary.Cloudinary;

import java.io.IOException;

import javax.inject.Inject;

import static com.cloudinary.utils.ObjectUtils.emptyMap;

public class CloudinaryServiceImpl implements CloudinaryService{


    private final Cloudinary cloudinary;

    @Inject
    public CloudinaryServiceImpl(CloudinaryConfig cloudinaryConfig) {
        cloudinary = cloudinaryConfig.getCloudinary();
    }

    @Override
    public void upload(Object photo) throws IOException {
        cloudinary.uploader().upload(photo, emptyMap());
    }
}
