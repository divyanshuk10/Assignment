package com.assignment.assignment.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.assignment.assignment.data.db.dao.GithubDao;
import com.assignment.assignment.model.Github;

/**
 * Created by Divyanshu  on 17/10/20.
 *
 */
@Database(entities = {
    Github.class}, version = 1, exportSchema = false)
public abstract class DBHelper extends RoomDatabase {
  private static final String NAME = "my-github-db";
  private static volatile DBHelper INSTANCE;

  public static DBHelper getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (DBHelper.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              DBHelper.class, NAME)
              // Wipes and rebuilds instead of migrating
              // if no Migration object.
              // Migration is not part of this practical.
              .fallbackToDestructiveMigration()
              .build();
        }
      }
    }
    return INSTANCE;
  }

  @Override public void close() {
    super.close();
    INSTANCE.close();
  }

  public abstract GithubDao githubDao();
}
