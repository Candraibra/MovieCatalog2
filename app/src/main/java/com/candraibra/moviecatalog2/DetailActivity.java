package com.candraibra.moviecatalog2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie selectedMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        Toolbar myToolbar = findViewById(R.id.toolbar_top);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        if (selectedMovie != null) {
            ImageView imgPoster = findViewById(R.id.img_poster);
            imgPoster.setImageResource(selectedMovie.getPoster());
            TextView txtTitle = findViewById(R.id.txt_title);
            txtTitle.setText(selectedMovie.getTitle());
            TextView txtDescription = findViewById(R.id.txt_description);
            txtDescription.setText(selectedMovie.getDescription());

        }
        Tvshow selectedTv = getIntent().getParcelableExtra(EXTRA_TV);

        if (selectedTv != null) {
            ImageView imgPoster = findViewById(R.id.img_poster);
            imgPoster.setImageResource(selectedTv.getPoster());
            TextView txtTitle = findViewById(R.id.txt_title);
            txtTitle.setText(selectedTv.getTitle());
            TextView txtDescription = findViewById(R.id.txt_description);
            txtDescription.setText(selectedTv.getDescription());

        }

    }
}