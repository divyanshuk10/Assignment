package com.assignment.assignment.network.helper;

/**
 * Created by Divyanshu  on 16/10/20
 */
public class BaseResponse<T> {
  public T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
