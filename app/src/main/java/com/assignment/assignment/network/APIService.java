package com.assignment.assignment.network;

import com.assignment.assignment.model.GithubResponse;
import io.reactivex.Observable;
import java.util.ArrayList;
import retrofit2.http.GET;

/**
 * Created by Divyanshu  on 16/10/20
 */
public interface APIService {
  @GET("repositories")
  Observable<ArrayList<GithubResponse>> getRepositories();
}
