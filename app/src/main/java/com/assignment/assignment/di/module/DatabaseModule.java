package com.assignment.assignment.di.module;

import android.app.Application;
import com.assignment.assignment.data.db.DBHelper;
import com.assignment.assignment.data.db.dao.GithubDao;
import com.assignment.assignment.network.APIService;
import com.assignment.assignment.repository.GithubRepo;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import com.assignment.assignment.repository.local.LocalGithubRepository;
import com.assignment.assignment.repository.remote.RemoteGithubRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Divyanshu  on 16/10/20
 */
@Module
public class DatabaseModule {
  private final DBHelper database;

  @Inject
  public DatabaseModule(Application application) {
    database = DBHelper.getDatabase(application);
  }

  @Provides
  @Singleton
  DBHelper providesDBHelper() {
    return database;
  }

  @Provides
  @Singleton
  GithubDao providesGithubDao(@NotNull DBHelper dbHelper) {
    return dbHelper.githubDao();
  }

  @Provides
  @Singleton
  RemoteGithubRepository providesRemoteGithubRepository(APIService api) {
    return new RemoteGithubRepository(api);
  }

  @Provides
  @Singleton
  LocalGithubRepository providesLocalRepository(DBHelper dbHelper) {
    return new LocalGithubRepository(dbHelper);
  }

  @Provides
  @Singleton
  GithubRepository providesGithubRepository(LocalGithubRepository localRepo,
      RemoteGithubRepository remoteRepo) {
    return new GithubRepo(remoteRepo, localRepo);
  }
}
