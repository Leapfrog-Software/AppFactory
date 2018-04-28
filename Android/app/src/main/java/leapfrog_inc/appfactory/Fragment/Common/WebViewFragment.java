package leapfrog_inc.appfactory.Fragment.Common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/28.
 */

public class WebViewFragment extends BaseFragment {

    private String mUrl;
    private String mTitle;

    public void set(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_webview, null);

        initContent(view);
        initAction(view);

        return view;
    }

    private void initContent(View view) {

        ((TextView)view.findViewById(R.id.titleTextView)).setText(mTitle);

        WebView webView = (WebView)view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mUrl);
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment(AnimationType.horizontal);
            }
        });
    }
}
