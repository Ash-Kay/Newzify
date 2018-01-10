package com.example.newzify;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ASHISH on 02-Jan-18.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView articleImage;
    TextView articleTitle;
    TextView articleDescription;

    CardView cardView;

    public ViewHolder(View itemView) {
        super(itemView);
        articleImage=itemView.findViewById(R.id.newsImage);
        articleTitle=itemView.findViewById(R.id.newsTitle);
        articleDescription=itemView.findViewById(R.id.newsDescription);
        cardView=itemView.findViewById(R.id.cardView);
    }
}
