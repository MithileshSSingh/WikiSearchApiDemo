package com.example.mithilesh.wikisearchapidemo.data.remote;

import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetSearchResult;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetWikiPage;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public class ApiClient {

    public interface APICalls {
        @GET(ServiceType.BASE_URL)
        Call<ResponseGetSearchResult> getSearchResult(
                @Query(QueryParams.KEY_ACTION) String action,
                @Query(QueryParams.KEY_FORMAT) String format,
                @Query(QueryParams.KEY_PROP) String prop,
                @Query(QueryParams.KEY_GENERATOR) String generator,
                @Query(QueryParams.KEY_REDIRECTS) String redirects,
                @Query(QueryParams.KEY_FORMAT_VERSION) String formatVersion,
                @Query(QueryParams.KEY_PIPROP) String piProp,
                @Query(QueryParams.KEY_PITHUMBSIZE) String piThumbsize,
                @Query(QueryParams.KEY_PILIMIT) String piLimits,
                @Query(QueryParams.KEY_WBPTTERMS) String wbPtterms,
                @Query(QueryParams.KEY_GPSSEARCH) String gpsSearch,
                @Query(QueryParams.KEY_GPSLIMIT) String gpsLimits
            );

        @GET(ServiceType.BASE_URL)
        Call<ResponseGetWikiPage> getWikiPage(
                @Query(QueryParams.KEY_ACTION) String action,
                @Query(QueryParams.KEY_FORMAT) String format,
                @Query(QueryParams.KEY_PROP) String prop,
                @Query(QueryParams.KEY_PAGE_ID) String pageId,
                @Query(QueryParams.KEY_INPROP) String inProp
        );
    }

    public static class ServiceType {
        public static final String BASE_URL = "https://en.wikipedia.org/w/api.php";

    }

    public static class HttpErrorCode {
        public static final Integer NO_CODE = 000;

    }
    public static class QueryParams {
        public static final String KEY_ACTION = "action";
        public static final String KEY_INPROP = "inprop";
        public static final String KEY_FORMAT = "format";
        public static final String KEY_PROP = "prop";
        public static final String KEY_GENERATOR = "generator";
        public static final String KEY_REDIRECTS = "redirects";
        public static final String KEY_FORMAT_VERSION = "formatversion";
        public static final String KEY_PIPROP = "piprop";
        public static final String KEY_PITHUMBSIZE = "pithumbsize";
        public static final String KEY_PILIMIT = "pilimit";
        public static final String KEY_WBPTTERMS = "wbptterms";
        public static final String KEY_GPSSEARCH = "gpssearch";
        public static final String KEY_PAGE_ID = "pageids";
        public static final String KEY_GPSLIMIT = "gpslimit";

        public static final String VALUE_ACTION = "query";
        public static final String VALUE_FORMAT = "json";
        public static final String VALUE_PROP = "pageimages|pageterms";
        public static final String VALUE_PROP_INFO = "info";
        public static final String VALUE_GENERATOR = "prefixsearch";
        public static final String VALUE_REDIRECTS = "1";
        public static final String VALUE_FORMAT_VERSION = "2";
        public static final String VALUE_PIPPROP = "thumbnail";
        public static final String VALUE_PITHUMBSIZE = "200";
        public static final String VALUE_PILIMIT = "10";
        public static final String VALUE_WBPTTERMS = "description";
        public static final String VALUE_GPSLIMIT = "10";
        public static final String VALUE_INPROP = "url";
    }
}
