package com.assignment.assignment.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.assignment.R;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.ui.main.MainViewModel;
import com.assignment.assignment.ui.main.adapter.RepoListViewAdapter;

public class FragmentOne extends Fragment {
  private static final String TAG = "FragmentOne";
  private MainViewModel mViewModel;
  private RecyclerView recyclerViewRepositoryList;
  private RepoListViewAdapter adapter;
  private View view;
  private OnItemClickedListener itemClickedListener;

  public static FragmentOne newFragmentOneInstance() {
    return new FragmentOne();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_one, container, false);
    findViews();
    return view;
  }

  private void findViews() {
    recyclerViewRepositoryList = view.findViewById(R.id.rlv_repositories);
    recyclerViewRepositoryList.setHasFixedSize(true);
    recyclerViewRepositoryList.setLayoutManager(new LinearLayoutManager(getContext()));
    adapter = new RepoListViewAdapter(repo -> {
      itemClickedListener.onItemClicked(repo);
    });
    recyclerViewRepositoryList.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    mViewModel.fetchGithubRepos();
    observeFetchGithubRepo();
  }

  private void observeFetchGithubRepo() {
    mViewModel.fetchRepoEvent.observe(getActivity(), eventState -> {
      switch (eventState) {
        case DISCONNECTED:
          Toast.makeText(getActivity(), "Disconnected", Toast.LENGTH_SHORT).show();
          break;
        case PROGRESS:
          Toast.makeText(getActivity(), "PROGRESS", Toast.LENGTH_SHORT).show();
          break;
        case SUCCESS:
          Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
          Log.d(TAG, "observeFetchGithubRepo: " + mViewModel.githubLiveData.getValue().toString());
          adapter.setRepoResponse(mViewModel.githubLiveData.getValue());
          break;
        case ERROR:
          Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
          break;
        case EMPTY_DATA:
          Toast.makeText(getActivity(), "EMPTY_DATA", Toast.LENGTH_SHORT).show();
          break;
        case LIMIT_REACHED:
          Toast.makeText(getActivity(), "LIMIT_REACHED", Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }

  @Override public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    itemClickedListener = (OnItemClickedListener) context;
  }

  public interface OnItemClickedListener {
    public void onItemClicked(GithubResponse data);
  }
}