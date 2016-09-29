package com.example.mysmall.app.main;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragmet = null;
    private RadioGroup radioGroup;
    private static boolean isExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().getFragments() != null) {
            getSupportFragmentManager().getFragments().clear();
        }
        radioGroup = (RadioGroup) findViewById(R.id.main_rg);
        fragments.add(new HomeFragment());
        fragments.add(new FilmFragment());
        fragments.add(new FoundFragment());
        fragments.add(new MineFragment());
        radioGroup.setOnCheckedChangeListener(occl);
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");
        if ("order".equals(tag) || "cart".equals(tag)) {
            ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
        }
        if ("mine".equals(tag)) {
            ((RadioButton) radioGroup.getChildAt(3)).setChecked(true);
        }
        intent.putExtra("tag", "");
    }

    private RadioGroup.OnCheckedChangeListener occl = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int index = group.indexOfChild(group.findViewById(checkedId));
            Fragment fragment = fragments.get(index);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currentFragmet != null) {
                ft.hide(currentFragmet);
            }
            if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().contains(fragment)) {
                ft.show(fragment);
            } else {
                ft.add(R.id.fragment_content, fragment);
            }
            currentFragmet = fragment;
            ft.commit();
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(),"再次点击退出",Toast.LENGTH_SHORT);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getSupportFragmentManager().getFragments() != null) {
            getSupportFragmentManager().getFragments().clear();
        }
    }
}
