package com.devst.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.flip_in, R.anim.flip_out);
    }
}
