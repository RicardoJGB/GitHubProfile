package com.mobileapps.githubprofile.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.mobileapps.githubprofile.R;

public class DetailActivity extends AppCompatActivity {
    TextView Link, Username;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView=findViewById(R.id.ivUserImage);
        Username= findViewById(R.id.tvUserName);

        Link = findViewById(R.id.tvLink);

        String username= getIntent().getExtras().getString("Login");
        String avatarUrl= getIntent().getExtras().getString("avatar_url");
        String link=getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link,Linkify.WEB_URLS);

        Username.setText(username);
      Glide.with(this).load(avatarUrl).into(imageView);

      getSupportActionBar().setTitle("Details Activity");
    }

    private Intent createShareForIntent(){
        String username= getIntent().getExtras().getString("Login");
        String link= getIntent().getExtras().getString("link");
        Intent shareIntent=ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check me out hehe @"+username+ ", "+link)
                .getIntent();
        return shareIntent;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_detail,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForIntent());
        return true;
    }
}
