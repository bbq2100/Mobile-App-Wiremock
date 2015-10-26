package novatec.cg.ema.wiremock.service;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestService {
    private static final String ROOT = "http://10.0.2.2:8080/api";
    private static RestApi BACKEND;

    static {
        setupRestService();
    }

    private RestService() {
    }

    public static RestApi getBackend() {
        return BACKEND;
    }

    private static void setupRestService() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        BACKEND = builder.build().create(RestApi.class);
    }
}
