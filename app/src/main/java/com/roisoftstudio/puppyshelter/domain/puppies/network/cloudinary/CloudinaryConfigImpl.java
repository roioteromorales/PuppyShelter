package com.roisoftstudio.puppyshelter.domain.puppies.network.cloudinary;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfigImpl implements CloudinaryConfig{

    private final Cloudinary cloudinary;

    public CloudinaryConfigImpl() {
        Map config = new HashMap();
        config.put("cloud_name", "roisoftstudio");
        config.put("api_key", "159188888492499");
        config.put("api_secret", "aCpIt_ogL3ho7cVBIniAd-tYYQk");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Cloudinary getCloudinary() {
        return cloudinary;
    }
}
