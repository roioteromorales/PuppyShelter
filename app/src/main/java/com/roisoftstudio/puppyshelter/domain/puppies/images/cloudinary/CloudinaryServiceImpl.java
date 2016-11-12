package com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.roisoftstudio.puppyshelter.domain.puppies.images.UploadCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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

    private class UploadPhotoTask extends AsyncTask<String, Void, String> {

        private Uploader uploader;
        private InputStream photo;
        private UploadCallback callback;

        public UploadPhotoTask(Uploader uploader, InputStream photo, UploadCallback callback) {
            this.uploader = uploader;
            this.photo = photo;
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            Map upload = null;

            try {
                upload = uploader.upload(photo,  ObjectUtils.asMap("transformation",
                        new Transformation().width(600)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return upload.get("url").toString();
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            callback.onResponse(url);

        }
    }
}
