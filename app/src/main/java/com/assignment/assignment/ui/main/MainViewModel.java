package com.assignment.assignment.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.assignment.assignment.Application;
import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.network.helper.EventState;
import com.assignment.assignment.network.helper.SingleLiveEvent;
import com.assignment.assignment.repository.interfaces.GithubRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu  on 16/10/20
 */
public class MainViewModel extends ViewModel {
  private static final String TAG = "MainViewModel";
  public SingleLiveEvent<EventState> fetchRepoEvent = new SingleLiveEvent<>();
  public SingleLiveEvent<EventState> fetchCommentEvent = new SingleLiveEvent<>();
  public SingleLiveEvent<EventState> addCommentEvent = new SingleLiveEvent<>();
  public MutableLiveData<ArrayList<GithubResponse>> githubLiveData = new MutableLiveData<>();
  public MutableLiveData<List<Github>> commentList = new MutableLiveData<>();
  private CompositeDisposable disposer = new CompositeDisposable();
  private GithubRepository repository =
      Application.getComponent().providesGithubRepository();

  public void fetchGithubRepos() {
    fetchRepoEvent.setValue(EventState.PROGRESS);
    disposer.add(repository.getRepositories().subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response != null && !response.isEmpty()) {
        githubLiveData.setValue(response);
        fetchRepoEvent.setValue(EventState.SUCCESS);
      } else {
        fetchRepoEvent.setValue(EventState.EMPTY_DATA);
      }
    }, throwable -> {
      if (throwable.getClass().equals(ConnectException.class)) {
        fetchRepoEvent.setValue(EventState.DISCONNECTED);
      } else {
        fetchRepoEvent.setValue(EventState.ERROR);
      }
    }));
  }

  public void fetchUserComment(Integer ownerId) {
    fetchCommentEvent.setValue(EventState.PROGRESS);
    disposer.add(repository.getComments(ownerId).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response != null && !response.isEmpty()) {
        commentList.setValue(response);
        fetchCommentEvent.setValue(EventState.SUCCESS);
      } else {
        fetchCommentEvent.setValue(EventState.EMPTY_DATA);
      }
    }, throwable -> {
      if (throwable.getClass().equals(ConnectException.class)) {
        fetchCommentEvent.setValue(EventState.DISCONNECTED);
      } else {
        fetchCommentEvent.setValue(EventState.ERROR);
      }
    }));
  }

  public void addComment(String comment, Integer ownerId) {
    Github newComment = new Github();
    newComment.ownerId = ownerId;
    newComment.comment = comment;
    addCommentEvent.setValue(EventState.PROGRESS);
    disposer.add(repository.addComment(newComment).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(response -> {
      if (response != null) {
      } else {
        addCommentEvent.setValue(EventState.EMPTY_DATA);
      }
    }, throwable -> {
      if (throwable.getClass().equals(ConnectException.class)) {
        addCommentEvent.setValue(EventState.DISCONNECTED);
      } else {
        addCommentEvent.setValue(EventState.ERROR);
      }
    }));
  }
}
