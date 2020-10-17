package com.assignment.assignment.repository.interfaces;

import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.repository.Repository;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu  on 17/10/20.
 */
public interface GithubRepository extends Repository {

  Observable<ArrayList<GithubResponse>> getRepositories();

  Observable<List<Github>> getComments(Integer ownerId);

  Single<Long> addComment(Github comment);
}
