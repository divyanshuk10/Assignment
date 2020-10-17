package com.assignment.assignment.repository.local;

import com.assignment.assignment.data.db.DBHelper;
import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Divyanshu  on 17/10/20.
 */
public class LocalGithubRepository implements GithubRepository {
  private final DBHelper dbHelper;

  @Inject public LocalGithubRepository(DBHelper dbHelper) {
    this.dbHelper = dbHelper;
  }

  @Override public Observable<ArrayList<GithubResponse>> getRepositories() {
    return null;
  }

  @Override public Observable<List<Github>> getComments(Integer ownerId) {
    return dbHelper.githubDao().getAllUsersComment(ownerId);
  }

  @Override public Single<Long> addComment(Github comment) {
    return dbHelper.githubDao().insertUserComment(comment);
  }
}
