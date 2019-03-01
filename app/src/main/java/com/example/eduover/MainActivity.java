package com.example.eduover;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private WebView sid;
    private static final String[] arr = {"https://"};
    private TextInputEditText auc;
    private View lin;
    private ProgressBar bar;
    private FloatingActionButton fab;
    private BottomAppBar bab;
    private String search_;
    private int dura;
    private boolean show = true;
    private Animation anim2;
    private Animation anim3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sid = findViewById(R.id.webview);
        bar = findViewById(R.id.progressBar3);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.lin);
        lin = findViewById(R.id.lin);
        lin.startAnimation(anim);
        lin.setVisibility(View.VISIBLE);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.load1);
        bar.startAnimation(anim1);
        bar.setVisibility(View.VISIBLE);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.load2);
        anim3 = AnimationUtils.loadAnimation(this,R.anim.load3);
        sid.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                bar.startAnimation(anim2);

            }

            public void onPageFinished(WebView view, String url) {
                bar.startAnimation(anim3);

            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                bar.startAnimation(anim3);

            }
        });
        auc = findViewById(R.id.texa);
        auc.addTextChangedListener(this);
        sid.getSettings().setJavaScriptEnabled(true);
        sid.loadUrl("https://google.com");
        fab  = findViewById(R.id.fab);
        bab = findViewById(R.id.bab);

        setSupportActionBar(bab);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.backm:
                if (sid.canGoBack()) {
                    sid.goBack();}
                break;
            case R.id.forwm:
                if(sid.canGoForward()) {
                    sid.goForward();
                }
                break;
            case R.id.refreshm:
                sid.reload();
        }
        return true;
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void setvis(View view) {
        dura = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        Animation anim = null;
        if (show) {
            anim = AnimationUtils.loadAnimation(this, R.anim.close);
            lin.startAnimation(anim);
            fab.setImageDrawable(getResources().getDrawable(R.drawable.close));
            show = false;
        } else {
            anim = AnimationUtils.loadAnimation(this, R.anim.open);
            lin.startAnimation(anim);
            fab.setImageDrawable(getResources().getDrawable(R.drawable.round_zoom_in_white_48dp));
            show = true;
        }


    }

    @Override
    public void onBackPressed() {
        if (sid.canGoBack()) {
            sid.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    public void opa(View view) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.open);
        search_ = auc.getText().toString();
        if (search_.isEmpty() || search_ == null){

        }
        else {
        if (search_.substring(0,5).equals("https")){
            sid.loadUrl((auc.getText()).toString());

        }
        else if(search_.substring(0,4).equals("http")) {
            sid.loadUrl((auc.getText()).toString());

        }
        else {
            sid.loadUrl("https://www.google.com/search?q=" + search_);

        } }
        lin.startAnimation(anim);
        show = true;
        fab.setImageDrawable(getResources().getDrawable(R.drawable.round_zoom_in_white_48dp));
    }

}
