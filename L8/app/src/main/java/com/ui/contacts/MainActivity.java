package com.ui.contacts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ui.fragments.AddContactFragment;
import com.ui.fragments.ContactsListFragment;

public class MainActivity extends AppCompatActivity {
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else {
            Fragment fragment = new ContactsListFragment();
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, fragment, null)
                    .commit();
        }
    }

    public void openAddUserFragment(View view) {
        Fragment fragment = new AddContactFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, null)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }

    public void onClick (){

    }

    public void replaceFragment(Class fragmentClass, Bundle args) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragment.setArguments(args);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
    }
}
