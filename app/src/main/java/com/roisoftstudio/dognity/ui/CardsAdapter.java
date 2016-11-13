package com.roisoftstudio.dognity.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.roisoftstudio.dognity.R;
import com.roisoftstudio.dognity.model.Animal;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<AnimalCardViewHolder> {
    private List<Animal> puppies;
    private Context context;

    public CardsAdapter(List<Animal> puppies) {
        this.puppies = puppies;
    }

    @Override
    public AnimalCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        CardView cardView = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.card_view, parent, false);

        return new AnimalCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(AnimalCardViewHolder holder, int position) {
        Animal animal = puppies.get(position);
        holder.render(animal);
    }

    @Override
    public int getItemCount() {
        return puppies.size();
    }

    public void setAnimals(List<Animal> puppies){
        this.puppies = puppies;
    }

}
