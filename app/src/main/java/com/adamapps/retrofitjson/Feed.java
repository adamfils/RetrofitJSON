package com.adamapps.retrofitjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class Feed {
    @SerializedName("totalResluts")
    @Expose
    private String totalResluts;
    @SerializedName("articles")
    @Expose
    private ArrayList<NewObject> articles;

    public String getTotalResluts() {
        return totalResluts;
    }

    public void setTotalResluts(String totalResluts) {
        this.totalResluts = totalResluts;
    }

    public ArrayList<NewObject> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewObject> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "totalResluts='" + totalResluts + '\'' +
                ", articles=" + articles +
                '}';
    }
}
