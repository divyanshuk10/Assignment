package com.assignment.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Divyanshu  on 17/10/20.
 */

@Entity(tableName = "github")
public class Github implements Parcelable {

  public static final Creator<Github> CREATOR = new Creator<Github>() {
    @Override
    public Github createFromParcel(Parcel in) {
      return new Github(in);
    }

    @Override
    public Github[] newArray(int size) {
      return new Github[size];
    }
  };
  @SerializedName("id")
  @ColumnInfo(name = "id")
  @PrimaryKey(autoGenerate = true)
  public Integer id;
  @SerializedName("ownerId")
  @ColumnInfo(name = "ownerId")
  public Integer ownerId;
  @SerializedName("comment")
  @ColumnInfo(name = "comment")
  public String comment;

  public Github() {
  }

  protected Github(Parcel in) {
    if (in.readByte() == 0) {
      id = null;
    } else {
      id = in.readInt();
    }
    if (in.readByte() == 0) {
      ownerId = null;
    } else {
      ownerId = in.readInt();
    }
    comment = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    if (id == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(id);
    }
    if (ownerId == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(ownerId);
    }
    dest.writeString(comment);
  }

  @Override
  public int describeContents() {
    return 0;
  }
}
