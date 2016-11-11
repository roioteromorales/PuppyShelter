package com.roisoftstudio.puppyshelter.domain.puppies.repositories;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;

import java.util.Arrays;
import java.util.List;

public class PuppiesRepositoryImpl implements PuppiesRepository {

    private String hipsterPuppy = "https://s-media-cache-ak0.pinimg.com/736x/6d/57/fc/6d57fc88efdf5286ef844aca8578712e.jpg";
    private String cutePuppy = "http://ghk.h-cdn.co/assets/16/09/980x490/landscape-1457107485-gettyimages-512366437.jpg";
    private String gangstaPuppy = "http://4.bp.blogspot.com/-m4scO7-J43k/T9wfjsg26VI/AAAAAAAACWY/dU4j5VM5u9A/s1600/Funny-Gangster-Animal-2.jpg";
    private String smartPuppy = "http://4.bp.blogspot.com/-GcMVyqd8b-g/Tbos88UAC_I/AAAAAAAAAVA/Twcsj2TfjcQ/s1600/4899369_VoTVqD3s_c.jpg";
    private String waterPuppy = "http://www.pupsor.com/wp-content/uploads/2016/03/water-puppy.jpg";
    private String swimmerPuppy = "http://petapixel.com/assets/uploads/2014/09/COREY_promo.jpg";


    @Override
    public List<Puppy> getAllPuppies() {
        return Arrays.asList(
                new Puppy("Hipster Puppy", hipsterPuppy),
                new Puppy("Cute Puppy", cutePuppy),
                new Puppy("Gangsta Puppy", gangstaPuppy),
                new Puppy("Smart Puppy", smartPuppy),
                new Puppy("Water Puppy", waterPuppy),
                new Puppy("Swimmer Puppy", swimmerPuppy)
        );
    }


}
