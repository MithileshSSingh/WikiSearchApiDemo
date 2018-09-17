package com.example.mithilesh.wikisearchapidemo.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetSearchResult {

    @SerializedName("batchcomplete")
    @Expose
    private Boolean batchcomplete;
    @SerializedName("continue")
    @Expose
    private Continue _continue;
    @SerializedName("query")
    @Expose
    private Query query;

    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Continue getContinue() {
        return _continue;
    }

    public void setContinue(Continue _continue) {
        this._continue = _continue;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public class Continue {

        @SerializedName("gpsoffset")
        @Expose
        private Integer gpsoffset;
        @SerializedName("continue")
        @Expose
        private String _continue;

        public Integer getGpsoffset() {
            return gpsoffset;
        }

        public void setGpsoffset(Integer gpsoffset) {
            this.gpsoffset = gpsoffset;
        }

        public String getContinue() {
            return _continue;
        }

        public void setContinue(String _continue) {
            this._continue = _continue;
        }

    }

    public class Page {

        @SerializedName("pageid")
        @Expose
        private Integer pageid;
        @SerializedName("ns")
        @Expose
        private Integer ns;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("index")
        @Expose
        private Integer index;
        @SerializedName("thumbnail")
        @Expose
        private Thumbnail thumbnail;
        @SerializedName("terms")
        @Expose
        private Terms terms;

        public Integer getPageid() {
            return pageid;
        }

        public void setPageid(Integer pageid) {
            this.pageid = pageid;
        }

        public Integer getNs() {
            return ns;
        }

        public void setNs(Integer ns) {
            this.ns = ns;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Terms getTerms() {
            return terms;
        }

        public void setTerms(Terms terms) {
            this.terms = terms;
        }

    }

    public class Query {

        @SerializedName("redirects")
        @Expose
        private List<Redirect> redirects = null;
        @SerializedName("pages")
        @Expose
        private List<Page> pages = null;

        public List<Redirect> getRedirects() {
            return redirects;
        }

        public void setRedirects(List<Redirect> redirects) {
            this.redirects = redirects;
        }

        public List<Page> getPages() {
            return pages;
        }

        public void setPages(List<Page> pages) {
            this.pages = pages;
        }

    }

    public class Redirect {

        @SerializedName("index")
        @Expose
        private Integer index;
        @SerializedName("from")
        @Expose
        private String from;
        @SerializedName("to")
        @Expose
        private String to;

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

    }

    public class Terms {

        @SerializedName("description")
        @Expose
        private List<String> description = null;

        public List<String> getDescription() {
            return description;
        }

        public void setDescription(List<String> description) {
            this.description = description;
        }

    }

    public class Thumbnail {

        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }

}
