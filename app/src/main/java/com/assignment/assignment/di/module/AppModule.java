package com.assignment.assignment.di.module;

import com.assignment.assignment.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Divyanshu  on 16/10/20
 */
@Module
public class AppModule {
  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application providesApplication() {
    return application;
  }

}
