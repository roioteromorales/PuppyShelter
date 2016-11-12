package com.roisoftstudio.puppyshelter.domain.puppies.images;

import android.os.AsyncTask;

import com.cloudinary.Transformation;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class UploadPhotoTask extends AsyncTask<String, Void, String> {

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
            upload = uploader.upload(photo, getTransformationsMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return upload.get("url").toString();
    }

    @Override
    protected void onPostExecute(String url) {
        callback.onResponse(url);

    }

    private Map getTransformationsMap() {
        return ObjectUtils.asMap("transformation", new Transformation().width(600));
    }
}
