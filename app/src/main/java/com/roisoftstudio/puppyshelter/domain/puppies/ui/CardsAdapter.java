package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<PuppyCardViewHolder> {
    private List<Puppy> puppies;
    private Context context;

    public CardsAdapter(List<Puppy> puppies) {
        this.puppies = puppies;
    }

    @Override
    public PuppyCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        CardView cardView = (CardView) LayoutInflater.from(context)
                .inflate(R.layout.card_view, parent, false);

        return new PuppyCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(PuppyCardViewHolder holder, int position) {
        Puppy puppy = puppies.get(position);
        holder.render(puppy);
    }

    @Override
    public int getItemCount() {
        return puppies.size();
    }
}
