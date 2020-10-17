package com.assignment.assignment.di;

import com.assignment.assignment.Application;
import com.assignment.assignment.data.db.DBHelper;
import com.assignment.assignment.di.module.AppModule;
import com.assignment.assignment.di.module.DatabaseModule;
import com.assignment.assignment.di.module.NetworkModule;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Divyanshu  on 16/10/20
 */

@Singleton
@Component(modules = { NetworkModule.class, AppModule.class, DatabaseModule.class })
public interface ApplicationComponent {

  Application getApplication();

  DBHelper providesDBHelper();

  GithubRepository providesGithubRepository();
}
