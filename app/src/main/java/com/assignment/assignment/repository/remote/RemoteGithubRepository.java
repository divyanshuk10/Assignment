package com.assignment.assignment.repository.remote;

import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.network.APIService;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Divyanshu  on 17/10/20.
 */
public class RemoteGithubRepository implements GithubRepository {
  private final APIService service;

  @Inject public RemoteGithubRepository(APIService service) {
    this.service = service;
  }

  @Override public Observable<ArrayList<GithubResponse>> getRepositories() {
    return service.getRepositories();
  }

  @Override public Observable<List<Github>> getComments(Integer ownerId) {
    return null;
  }

  @Override public Single<Long> addComment(Github comment) {
    return null;
  }
}
