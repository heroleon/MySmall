package com.example.mysmall.app.main;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.widget.TextView;

import net.wequick.small.Small;

/**
 * Created by Administrator on 2016/9/23.
 */
public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sub);
        TextView tv= (TextView) findViewById(R.id.tv);
        //获取宿主的资源
        //tv.setText(Small.getContext().getResources().getIdentifier("login","string","com.example.mysmall"));

        //插件间的参数传递
        //Uri uri = Small.getUri(this);
        //tv.setText(uri.getQueryParameter("para"));

    }
}
