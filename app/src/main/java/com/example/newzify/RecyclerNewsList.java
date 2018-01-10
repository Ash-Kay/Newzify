package com.example.newzify;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RecyclerNewsList extends AppCompatActivity {

    public ArrayList<NewsArticle> newsArticleArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_news_list);

        Bundle bundle=getIntent().getExtras();
        String isTop= bundle.getString("isTop");
        String searchQuery=bundle.getString("searchQuery");

        searchQuery= URLEncoder.encode(searchQuery);
        System.out.println(searchQuery);

        String headEvery;
        if(isTop.compareTo("true")==0)
            headEvery="top-headlines";
        else
            headEvery="everything";

        String url="https://newsapi.org/v2/" + headEvery + "?q=" + searchQuery + "&apiKey=API_KEY";

        if(searchQuery.compareTo("")==0){
            url="https://newsapi.org/v2/" + headEvery + "?sources=the-verge&sortBy=popularity&apiKey=API_KEY";
        }

        String downData="";
        DownloadTask downloadTask=new DownloadTask();
        String article;

        try {

            downData=downloadTask.execute(url).get();
            System.out.println(url);
            System.out.println(downData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {

            JSONObject jsonObject=new JSONObject(downData);
            article=jsonObject.getString("articles");
            JSONArray jsonArray=new JSONArray(article);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonElement =jsonArray.getJSONObject(i);
                String title;
                String description;
                String articleUrl;
                String articleImage;

                title=jsonElement.getString("title");
                description=jsonElement.getString("description");
                articleUrl=jsonElement.getString("url");
                articleImage=jsonElement.getString("urlToImage");

                newsArticleArrayList.add(new NewsArticle(articleImage,title,description,articleUrl));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView=findViewById(R.id.recyclerViewList);
        RecyclerAdapter adapter =new RecyclerAdapter(this,newsArticleArrayList);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        if(newsArticleArrayList.size()==0){
            Toast.makeText(this,"No Result try Everything Mode!",Toast.LENGTH_LONG).show();
        }


    }

    public static class DownloadTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {

            String downString="";
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url=new URL(strings[0]);
                httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                int data=inputStreamReader.read();
                while(data!=-1){
                    char ch= (char) data;
                    downString+=ch;
                    data=inputStreamReader.read();
                }

                return downString;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
