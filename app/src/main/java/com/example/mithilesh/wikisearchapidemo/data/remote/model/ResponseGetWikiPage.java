package com.example.mithilesh.wikisearchapidemo.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ResponseGetWikiPage {

    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;
    @SerializedName("query")
    @Expose
    private Query query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }


    public class Query {

        @SerializedName("pages")
        @Expose
        private HashMap<String, PageInfo> pages;

        public HashMap<String, PageInfo> getPages() {
            return pages;
        }

        public void setPages(HashMap<String, PageInfo> pages) {
            this.pages = pages;
        }

    }

}
