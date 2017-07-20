package com.drawthink.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    TextFragment textFragment;
    ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        showFragment(R.id.text_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        showFragment(id);
        return false;
    }

    private void showFragment(int viewId) {
        FragmentTransaction ft = fm.beginTransaction();
        switch (viewId) {
            case R.id.text_fragment:
                if (textFragment == null) {
                    textFragment = new TextFragment();
                    ft.add(R.id.fragment_container, textFragment);
                } else {
                    ft.show(textFragment);
                }
                if (null != imageFragment) {
                    ft.hide(imageFragment);
                }
                break;
            case R.id.image_fragment:
                if (imageFragment == null) {
                    imageFragment = new ImageFragment();
                    ft.add(R.id.fragment_container, imageFragment);
                } else {
                    ft.show(imageFragment);
                }
                if (null != textFragment) {
                    ft.hide(textFragment);
                }
                break;
        }
        ft.commit();
    }


}