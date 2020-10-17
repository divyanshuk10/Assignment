package com.assignment.assignment.data.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Divyanshu  on 17/10/20.
 *
 */
public class BaseResponse<T> {
  @SerializedName("message")
  @Expose
  public String message;
  @SerializedName("response")
  @Expose
  public Boolean response;
  @SerializedName("data")
  @Expose
  public T data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getResponse() {
    return response;
  }

  public void setResponse(Boolean response) {
    this.response = response;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
