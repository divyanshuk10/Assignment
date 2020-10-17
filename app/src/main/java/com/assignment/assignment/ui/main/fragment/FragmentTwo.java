package com.assignment.assignment.ui.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.assignment.Application;
import com.assignment.assignment.R;
import com.assignment.assignment.di.module.GlideApp;
import com.assignment.assignment.model.Github;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.ui.main.MainViewModel;
import com.assignment.assignment.ui.main.adapter.CommentListViewAdapter;
import com.google.gson.Gson;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FragmentTwo extends Fragment {
  public static final String ARG_ONE = "ARG1";
  private MainViewModel mViewModel;
  private GithubResponse data = new GithubResponse();
  private AppCompatTextView textView2;
  private AppCompatTextView textView;
  private AppCompatImageView imageView2;
  private RecyclerView recyclerViewComments;
  private CommentListViewAdapter adapter;
  private View view;

  public static FragmentTwo newFragmentTwoInstance() {
    return new FragmentTwo();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_two, container, false);
    findViews();
    return view;
  }

  private void findViews() {
    textView2 = view.findViewById(R.id.textView2);
    textView = view.findViewById(R.id.imageView);
    imageView2 = view.findViewById(R.id.imageView2);
    recyclerViewComments = view.findViewById(R.id.rlv_comments);
    recyclerViewComments.setHasFixedSize(true);
    recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
    adapter = new CommentListViewAdapter();
    recyclerViewComments.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
  }

  public void setData(GithubResponse response) {
    data = response;
    adapter.clearData();
    populateData();
  }

  private void populateData() {
    Gson gson = new Gson();
    String json = gson.toJson(data);
    textView2.setText(String.format("%s", json));

    GlideApp.with(Application.getComponent().getApplication())
        .load(data.getOwner().getAvatarUrl())
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
        .fitCenter()
        .into(imageView2);
    textView.setOnClickListener(v -> {
      showEditTextAlert();
    });
    fetchComments(data.getOwner().getId());
  }

  private void showEditTextAlert() {
    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
    final EditText edittext = new EditText(getContext());
    alert.setMessage("Add your comment");
    alert.setTitle("Comment");

    alert.setView(edittext);

    alert.setPositiveButton("Add", (dialog, whichButton) -> {
      String comment = edittext.getText().toString();
      addComment(comment, data.getOwner().getId());
      dialog.dismiss();
    });

    alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
      dialog.dismiss();
    });

    alert.show();
  }

  private void addComment(String comment, Integer id) {
    mViewModel.addComment(comment, id);
    observeAddComment();
  }

  private void observeAddComment() {
    mViewModel.addCommentEvent.observe(getActivity(), eventState -> {
      switch (eventState) {
        case DISCONNECTED:
          Toast.makeText(getActivity(), "Disconnected", Toast.LENGTH_SHORT).show();
          break;
        case PROGRESS:
          Toast.makeText(getActivity(), "PROGRESS", Toast.LENGTH_SHORT).show();
          break;
        case SUCCESS:
          Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
          fetchComments(data.getOwner().getId());
          break;
        case ERROR:
          Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
          break;
        case EMPTY_DATA:
          Toast.makeText(getActivity(), "No Comments", Toast.LENGTH_SHORT).show();
          break;
        case LIMIT_REACHED:
          Toast.makeText(getActivity(), "LIMIT_REACHED", Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }

  private void fetchComments(int ownerId) {
    mViewModel.fetchUserComment(ownerId);
    observeUserComment();
  }

  private void observeUserComment() {
    mViewModel.fetchCommentEvent.observe(getActivity(), eventState -> {
      switch (eventState) {
        case DISCONNECTED:
          Toast.makeText(getActivity(), "Disconnected", Toast.LENGTH_SHORT).show();
          break;
        case PROGRESS:
          Toast.makeText(getActivity(), "PROGRESS", Toast.LENGTH_SHORT).show();
          break;
        case SUCCESS:
          List<Github> commentsList = mViewModel.commentList.getValue();
          adapter.clearData();
          adapter.setRepoResponse(commentsList);
          Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
          break;
        case ERROR:
          Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
          break;
        case EMPTY_DATA:
          Toast.makeText(getActivity(), "No Comments", Toast.LENGTH_SHORT).show();
          break;
        case LIMIT_REACHED:
          Toast.makeText(getActivity(), "LIMIT_REACHED", Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }

  @Override public void setMenuVisibility(boolean menuVisible) {
    super.setMenuVisibility(menuVisible);
    Toast.makeText(getActivity(), "VISINBLE", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "setMenuVisibility: VISIBLE NOW");
    Bundle bundle = this.getArguments();
    Log.d(TAG, "setMenuVisibility: " + bundle.isEmpty());
  }
}