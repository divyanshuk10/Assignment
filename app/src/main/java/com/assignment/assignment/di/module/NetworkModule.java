package com.assignment.assignment.di.module;

import com.assignment.assignment.Application;
import com.assignment.assignment.network.APIService;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Divyanshu  on 16/10/20
 */
@Module
public class NetworkModule {
  public static final String BASE_URL = "https://api.github.com/";
  private static final long CONNECTION_TIME_OUT = 30; // 10 sec
  private static final long READ_TIME_OUT = 30; // 30 sec
  private static final long WRITE_TIME_OUT = 30; // 30 sec

  @Inject
  public NetworkModule() {
  }

  @Singleton
  @Provides
  static OkHttpClient provideOkhttpClient(Application application) {
    return new OkHttpClient().newBuilder()
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(new HttpLoggingInterceptor())
        .addInterceptor(new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .followRedirects(false)
        .build();
  }

  @Singleton
  @Provides
  static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build();
  }

  @Provides
  @Singleton
  public static APIService providesApiService(Retrofit retrofit) {
    return retrofit.create(APIService.class);
  }
}