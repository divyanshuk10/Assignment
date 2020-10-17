package com.assignment.assignment.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.assignment.R;
import com.assignment.assignment.model.Github;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu  on 17/10/20
 */
public class CommentListViewAdapter
    extends RecyclerView.Adapter<CommentListViewAdapter.CommentViewHolder> {

  private List<Github> commentsList;

  public CommentListViewAdapter() {
    commentsList = new ArrayList<>();
  }

  @NonNull @Override
  public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_comment, parent, false);
    final CommentViewHolder
        holder = new CommentViewHolder(itemView);
    holder.itemView.setOnClickListener(
        v -> {

        });
    return holder;
  }

  @Override public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
    Github repo = commentsList.get(position);
    holder.txtComment.setText(String.format("%s", repo.comment));
  }

  @Override public int getItemCount() {
    return commentsList.size() == 0 ? -1 : commentsList.size();
  }

  public void setRepoResponse(List<Github> list) {
    this.commentsList.addAll(list);
    notifyDataSetChanged();
  }

  public void clearData() {
    commentsList.clear();
    notifyDataSetChanged();
  }

  static class CommentViewHolder extends RecyclerView.ViewHolder {
    private AppCompatTextView txtComment;

    CommentViewHolder(@NonNull View view) {
      super(view);
      txtComment = view.findViewById(R.id.txt_comment);
    }
  }
}
