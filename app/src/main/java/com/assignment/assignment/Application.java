package com.assignment.assignment;

import android.content.Context;
import android.content.Intent;
import androidx.multidex.MultiDex;
import com.assignment.assignment.di.ApplicationComponent;
import com.assignment.assignment.di.DaggerApplicationComponent;
import com.assignment.assignment.di.module.AppModule;
import com.assignment.assignment.di.module.DatabaseModule;
import com.facebook.stetho.Stetho;

/**
 * Created by Divyanshu  on 16/10/20
 */
public class Application extends android.app.Application {
  private static ApplicationComponent applicationComponent;

  public static ApplicationComponent getComponent() {
    return applicationComponent;
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .appModule(new AppModule(this))
        .databaseModule(new DatabaseModule(this))
        .build();


    Stetho.initialize(Stetho.newInitializerBuilder(getApplicationContext())
        .enableDumpapp(() -> new Stetho.DefaultDumperPluginsBuilder(getApplicationContext())
            .finish())
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(getApplicationContext()))
        .build());

  }

  @Override public boolean stopService(Intent name) {
    return super.stopService(name);
  }
}
