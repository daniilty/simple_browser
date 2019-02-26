package com.example.eduover;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private LinearLayout lin;
    private ProgressBar bar;
    private FloatingActionButton fab;
    private BottomAppBar bab;
    private String search_;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sid = findViewById(R.id.webview);
        bar = findViewById(R.id.progressBar3);
        sid.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                bar.setVisibility(View.VISIBLE);

            }

            public void onPageFinished(WebView view, String url) {
                bar.setVisibility(View.GONE);

            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                bar.setVisibility(View.GONE);

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
        lin = findViewById(R.id.lin);
        lin.setVisibility(View.VISIBLE);

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
        search_ = auc.getText().toString();
        if (search_.substring(0,5).equals("https")){
            sid.loadUrl((auc.getText()).toString());
        }
        else if(search_.substring(0,4).equals("http")) {
            sid.loadUrl((auc.getText()).toString());
        }
        else {
            sid.loadUrl("https://www.google.com/search?q=" + search_);
        }
        lin = findViewById(R.id.lin);
        lin.setVisibility(View.INVISIBLE);
    }
    public void refreshdemo(View view) {
        sid.reload();

    }
    public void forw(View view) {
        if(sid.canGoForward()) {
            sid.goForward();
        }
    }
}
