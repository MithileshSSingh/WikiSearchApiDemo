package com.example.mithilesh.wikisearchapidemo.data.remote;

import android.content.Context;
import android.text.TextUtils;

import com.example.mithilesh.wikisearchapidemo.data.DataSource;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetSearchResult;
import com.example.mithilesh.wikisearchapidemo.data.remote.model.ResponseGetWikiPage;
import com.example.mithilesh.wikisearchapidemo.utils.AppPref;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;
    private static Retrofit retrofit;
    private static ApiClient.APICalls apiCalls;
    private Context mContext;

    private RemoteDataSource() {

    }

    private RemoteDataSource(Context context) {
        mContext = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }

        return INSTANCE;
    }

    public synchronized ApiClient.APICalls getApiClient() {
        if (apiCalls == null) {
            apiCalls = getRetrofitInstance().create(ApiClient.APICalls.class);
        }

        return apiCalls;
    }

    private synchronized Retrofit getRetrofitInstance() {
        //XXX
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();

            client.setProtocols(Arrays.asList(com.squareup.okhttp.Protocol.HTTP_1_1));

            client.networkInterceptors().add(new com.facebook.stetho.okhttp.StethoInterceptor());

            client.setReadTimeout(10, TimeUnit.SECONDS);
            client.setWriteTimeout(10, TimeUnit.SECONDS);
            client.setConnectTimeout(10, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder().baseUrl(ApiClient.ServiceType.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor())).build();
        }

        return retrofit;
    }

    private ApiError getApiError(Throwable t) {
        RetrofitException exception = (RetrofitException) t;
        ApiError apiError = new ApiError();

        if (exception.getErrorResponse() != null) {
            ApiErrorResponse apiErrorResponse = exception.getErrorResponse();
            if (apiErrorResponse != null) {

                apiError.errorCode = apiErrorResponse.getStatusCode();
                apiError.msgError = apiErrorResponse.getMessage();

            } else {

                apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
                apiError.msgError = "Error";

            }
        } else {

            apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
            apiError.msgError = "Error";

        }

        return apiError;
    }

    @Override
    public void getSearchResult(String query, final GetSerchResultCallBack callBack) {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }


        final Call<ResponseGetSearchResult> call = getApiClient().getSearchResult(ApiClient.QueryParams.VALUE_ACTION, ApiClient.QueryParams.VALUE_FORMAT, ApiClient.QueryParams.VALUE_PROP, ApiClient.QueryParams.VALUE_GENERATOR, ApiClient.QueryParams.VALUE_REDIRECTS, ApiClient.QueryParams.VALUE_FORMAT_VERSION, ApiClient.QueryParams.VALUE_PIPPROP, ApiClient.QueryParams.VALUE_PITHUMBSIZE, ApiClient.QueryParams.VALUE_PILIMIT, ApiClient.QueryParams.VALUE_WBPTTERMS, query, ApiClient.QueryParams.VALUE_GPSLIMIT);


        call.enqueue(new Callback<ResponseGetSearchResult>() {
            @Override
            public void onResponse(retrofit.Response<ResponseGetSearchResult> response, Retrofit retrofit) {

                ResponseGetSearchResult responseCustomer = response.body();

                callBack.success(responseCustomer);
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }

    @Override
    public void getWikiDetail(String pageId, final GetWikiDetailCallBack callBack) {
        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "No Network");
            return;
        }


        final Call<ResponseGetWikiPage> call = getApiClient().getWikiPage(
                ApiClient.QueryParams.VALUE_ACTION,
                ApiClient.QueryParams.VALUE_FORMAT,
                ApiClient.QueryParams.VALUE_PROP_INFO,
                pageId,
                ApiClient.QueryParams.VALUE_INPROP
        );


        call.enqueue(new Callback<ResponseGetWikiPage>() {
            @Override
            public void onResponse(retrofit.Response<ResponseGetWikiPage> response, Retrofit retrofit) {

                ResponseGetWikiPage responseCustomer = response.body();

                callBack.success(responseCustomer);
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });
    }

    private class ApiError {
        public int errorCode;
        public String msgError;

        @Override
        public String toString() {
            return "ApiError{" + "errorCode=" + errorCode + ", msgError=" + msgError + '}';
        }
    }
}
