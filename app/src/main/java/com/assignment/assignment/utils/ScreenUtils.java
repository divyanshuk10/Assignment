package com.assignment.assignment.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.snackbar.Snackbar;
import java.lang.ref.WeakReference;

/**
 * Created by Divyanshu  on 01/09/20
 */
public class ScreenUtils {
  public static void hideKeyboard(WeakReference<Activity> activity) {
    InputMethodManager imm =
        (InputMethodManager) activity.get().getSystemService(Activity.INPUT_METHOD_SERVICE);
    View view = activity.get().getCurrentFocus();
    if (view == null) {
      view = new View(activity.get());
    }
    if (imm != null) {
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  /**
   * @param view View to animate
   * @param toVisibility Visibility at the end of animation
   * @param toAlpha Alpha at the end of animation
   * @param duration Animation duration in ms
   */
  public static void animateView(final View view, final int toVisibility, float toAlpha,
      int duration) {
    boolean show = toVisibility == View.VISIBLE;
    if (show) {
      view.setAlpha(0);
    }
    view.setVisibility(View.VISIBLE);
    view.animate()
        .setDuration(duration)
        .alpha(show ? toAlpha : 0)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            view.setVisibility(toVisibility);
          }
        });
  }

  public static void makeSnackBar(View view, String message, int length) {
    Snackbar.make(view, message, length).show();
  }
}
