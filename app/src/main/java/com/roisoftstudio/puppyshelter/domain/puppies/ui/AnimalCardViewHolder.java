package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalCardViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView puppyPhoto;
    private CardView view;
    private Animal animal;

    public AnimalCardViewHolder(CardView view) {
        super(view);
        this.view = view;
        title = (TextView) view.findViewById(R.id.title);
        puppyPhoto = (ImageView) view.findViewById(R.id.puppy_photo);

        setOnClickListener();
    }

    private void setOnClickListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(view.getContext(), "selected " + title.getText(), Toast.LENGTH_SHORT).show();

                AnimalDetailActivity.open(view.getContext(), animal);
            }
        });
    }

    public void render(Animal animal) {
        this.animal = animal;
        title.setText(animal.getName());
        Picasso.with(view.getContext())
                .load(animal.getImageUrl())
                .placeholder(R.drawable.error_missing_photo)
                .into(puppyPhoto);
    }
}
