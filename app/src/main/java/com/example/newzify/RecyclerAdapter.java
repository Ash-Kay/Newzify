package com.example.newzify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASHISH on 02-Jan-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<NewsArticle> newsArticleArrayList;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<NewsArticle> newsArticleArrayList) {
        this.newsArticleArrayList = newsArticleArrayList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsArticle newsArticle=newsArticleArrayList.get(position);
        holder.articleTitle.setText(newsArticle.getTitle());
        holder.articleDescription.setText(newsArticle.getDescription());

        Picasso.with(context).load(newsArticle.getImageUrl()).resize(MainActivity.width,MainActivity.width)
                .centerCrop().into(holder.articleImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri=newsArticle.getUrl();
                System.out.println(uri);

                Intent intent=new Intent(context,Webview.class);
                intent.putExtra("url",uri);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
       return newsArticleArrayList.size();
    }

}
