package com.adamapps.retrofitjson;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    String BASE_URL = "https://newsapi.org/v2/";
    ArrayList<String> aurthorText = new ArrayList<>();
    ArrayList<String> titleText = new ArrayList<>();
    ArrayList<String> descText = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



    }

    public void GetJSON(View view) {

        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<Feed> feedCall = redditAPI.getArticles();

        feedCall.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {

                NewObject JustTest = new NewObject();
                ArrayList<NewObject> feeds = response.body().getArticles();

                for(NewObject test: feeds ){
                    aurthorText.add(test.getAuthor());
                    titleText.add(test.getTitle());
                    descText.add(test.getDescription());
                    //Toast.makeText(MainActivity.this, ""+test.getTitle(), Toast.LENGTH_SHORT).show();
                }

                recyclerView.setAdapter(new NewListAdapter());
                assert response.body() != null;
                //Toast.makeText(MainActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class NewListAdapter extends RecyclerView.Adapter<NewsHolder>{

        /*ArrayList<NewObject>  testingObject;

        public NewListAdapter(ArrayList<NewObject> testingObject) {
            this.testingObject = testingObject;
        }*/

        @NonNull
        @Override
        public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.news_list,parent,false);
            return new NewsHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
            holder.newsAurthur.setText("Arthur :  "+aurthorText.get(position));
            holder.newsTitle.setText(titleText.get(position));
            holder.newsDesc.setText(descText.get(position));
        }

        @Override
        public int getItemCount() {
            return descText.size();
        }
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        TextView newsTitle,newsDesc,newsAurthur;

        public NewsHolder(View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.news_title);
            newsDesc = itemView.findViewById(R.id.news_desc);
            newsAurthur = itemView.findViewById(R.id.news_author);
        }
    }
}
