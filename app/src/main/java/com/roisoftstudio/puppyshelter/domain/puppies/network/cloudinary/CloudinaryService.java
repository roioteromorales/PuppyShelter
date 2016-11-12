package com.roisoftstudio.puppyshelter.domain.puppies.network.cloudinary;

import java.io.IOException;

public interface CloudinaryService {
    void upload(Object photo) throws IOException;
}
