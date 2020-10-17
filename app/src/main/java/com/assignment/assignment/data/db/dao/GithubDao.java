package com.assignment.assignment.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.assignment.assignment.model.Github;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu  on 17/10/20.
 */
@Dao
public interface GithubDao {

  @Query("SELECT * FROM github LIMIT 1")
  Observable<Github> getUserComment();

  @Query("SELECT * FROM github WHERE ownerId=:ownerId")
  Observable<List<Github>> getAllUsersComment(Integer ownerId);

  @Insert(entity = Github.class, onConflict = OnConflictStrategy.REPLACE)
  Single<Long> insertUserComment(Github comment);

  @Update(entity = Github.class)
  Completable updateUserComment(Github comment);

  @Query("DELETE FROM github")
  Completable deleteUserComment();
}
