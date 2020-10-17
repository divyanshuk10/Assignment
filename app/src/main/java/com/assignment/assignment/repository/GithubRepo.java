package com.assignment.assignment.repository;

import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import com.assignment.assignment.repository.local.LocalGithubRepository;
import com.assignment.assignment.repository.remote.RemoteGithubRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Divyanshu  on 17/10/20.
 */
public class GithubRepo implements GithubRepository {
  private final RemoteGithubRepository remoteRepository;
  private final LocalGithubRepository localRepository;

  @Inject
  public GithubRepo(
      RemoteGithubRepository remoteRepository,
      LocalGithubRepository localRepository) {
    this.remoteRepository = remoteRepository;
    this.localRepository = localRepository;
  }

  @Override public Observable<ArrayList<GithubResponse>> getRepositories() {
    return remoteRepository.getRepositories();
  }

  @Override public Observable<List<Github>> getComments(Integer ownerId) {
    return localRepository.getComments(ownerId);
  }

  @Override public Single<Long> addComment(Github comment) {
    return localRepository.addComment(comment);
  }
}
