package com.app.code;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class webviewbase extends AppCompatActivity {
    WebView webView;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    final String url="https://console.firebase.google.com/u/1/project/sites-c7687/authentication/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewbase);




        webView = (WebView) findViewById(R.id.webview);


        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(webviewbase.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(url);
            }
        });*/


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(webviewbase.this, "RETRY AGAIN-----Error:" + description, Toast.LENGTH_SHORT).show();

            }

        });
        webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) webView.goBack();

        else {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Do you want to EXIT").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });


            AlertDialog alertDialog=builder.create();
            alertDialog.show();

        }
    }


}