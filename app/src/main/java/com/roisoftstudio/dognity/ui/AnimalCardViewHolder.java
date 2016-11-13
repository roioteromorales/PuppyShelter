package com.roisoftstudio.dognity.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.roisoftstudio.dognity.R;
import com.roisoftstudio.dognity.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalCardViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView animalPhoto;
    private CardView view;
    private Animal animal;

    public AnimalCardViewHolder(CardView view) {
        super(view);
        this.view = view;
        title = (TextView) view.findViewById(R.id.title);
        animalPhoto = (ImageView) view.findViewById(R.id.puppy_photo);
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                AnimalDetailActivity.open(AnimalCardViewHolder.this.view.getContext(), animal);
            }
        });
    }

    public void render(Animal animal) {
        this.animal = animal;
        title.setText(animal.getName());
        Picasso.with(view.getContext())
                .load(animal.getImageUrl())
                .placeholder(R.drawable.error_missing_photo)
                .into(animalPhoto);
    }
}
