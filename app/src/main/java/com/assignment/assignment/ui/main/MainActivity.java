package com.assignment.assignment.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.assignment.assignment.R;
import com.assignment.assignment.model.GithubResponse;
import com.assignment.assignment.ui.main.fragment.FragmentOne;
import com.assignment.assignment.ui.main.fragment.FragmentThree;
import com.assignment.assignment.ui.main.fragment.FragmentTwo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.assignment.assignment.ui.main.fragment.FragmentOne.newFragmentOneInstance;
import static com.assignment.assignment.ui.main.fragment.FragmentThree.newFragmentThreeInstance;
import static com.assignment.assignment.ui.main.fragment.FragmentTwo.newFragmentTwoInstance;

/**
 * Created by Divyanshu  on 16/10/20
 */
public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener,
    FragmentOne.OnItemClickedListener {

  private static final String TAG = "MainActivity";
  private final FragmentOne fragment1 = newFragmentOneInstance();
  private final FragmentTwo fragment2 = newFragmentTwoInstance();
  private final FragmentThree fragment3 = newFragmentThreeInstance();
  private final FragmentManager fm = getSupportFragmentManager();
  private Fragment active = fragment1;
  private BottomNavigationView bottomNavigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViews();
  }

  private void findViews() {
    bottomNavigationView = findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);

    fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
    fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
    fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_tab_1:
        fm.beginTransaction().hide(active).show(fragment1).commit();
        active = fragment1;
        return true;
      case R.id.action_tab_2:
        fm.beginTransaction().hide(active).show(fragment2).commit();
        active = fragment2;
        return true;
      case R.id.action_tab_3:
        fm.beginTransaction().hide(active).show(fragment3).commit();
        active = fragment3;
        return true;
    }
    return false;
  }

  @Override public void onItemClicked(GithubResponse data) {
    fragment2.setData(data);
    bottomNavigationView.setSelectedItemId(R.id.action_tab_2);
  }
}