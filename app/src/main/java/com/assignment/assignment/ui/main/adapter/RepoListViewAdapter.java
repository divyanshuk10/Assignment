package com.assignment.assignment.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.assignment.Application;
import com.assignment.assignment.R;
import com.assignment.assignment.di.module.GlideApp;
import com.assignment.assignment.model.GithubResponse;
import java.util.ArrayList;

/**
 * Created by Divyanshu  on 17/10/20
 */
public class RepoListViewAdapter
    extends RecyclerView.Adapter<RepoListViewAdapter.RepoViewHolder> {

  private ArrayList<GithubResponse> repoList;
  private OnRepoClickListener listener;

  public RepoListViewAdapter(OnRepoClickListener listener) {
    this.listener = listener;
    repoList = new ArrayList<>();
  }

  @NonNull @Override
  public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_repo, parent, false);
    final RepoViewHolder
        holder = new RepoViewHolder(itemView);
    holder.itemView.setOnClickListener(
        v -> listener.onRepoItemClicked(repoList.get(holder.getAdapterPosition())));
    return holder;
  }

  @Override public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
    GithubResponse repo = repoList.get(position);
    holder.txtRepoTitle.setText(String.format("%s", repo.getDescription()));
    holder.txtAuthor.setText(String.format("%s", repo.getFullName()));

    GlideApp.with(Application.getComponent().getApplication())
        .load(repo.getOwner().getAvatarUrl())
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
        .fitCenter()
        .into(holder.imgRepo);
  }

  @Override public int getItemCount() {
    return repoList.size() == 0 ? -1 : repoList.size();
  }

  public void setRepoResponse(ArrayList<GithubResponse> list) {
    this.repoList.addAll(list);
    notifyDataSetChanged();
  }

  public void clearData() {
    repoList.clear();
    notifyDataSetChanged();
  }

  public interface OnRepoClickListener {
    void onRepoItemClicked(GithubResponse Repo);
  }

  static class RepoViewHolder extends RecyclerView.ViewHolder {
    private AppCompatTextView txtRepoTitle;
    private AppCompatTextView txtAuthor;
    private AppCompatImageView imgRepo;

    RepoViewHolder(@NonNull View view) {
      super(view);
      txtRepoTitle = view.findViewById(R.id.txtRepoTitle);
      txtAuthor = view.findViewById(R.id.txtAuthor);
      imgRepo = view.findViewById(R.id.imgRepo);
    }
  }
}
