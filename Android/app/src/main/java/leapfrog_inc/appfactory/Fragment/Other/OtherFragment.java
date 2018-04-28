package leapfrog_inc.appfactory.Fragment.Other;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Common.WebViewFragment;
import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Function.SaveData;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class OtherFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_other, null);

        initContent(view);
        initAction(view);

        return view;
    }

    private void initContent(View view) {

        String version = "-.-.-";

        Context context = getActivity().getApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e) {}

        ((TextView)view.findViewById(R.id.versionTextView)).setText("バージョン : " + version);
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.pushButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getView();
                if (view == null) return;

                SaveData saveData = SaveData.getInstance();

                CheckBox checkBox = (CheckBox)view.findViewById(R.id.pushCheckBox);
                if (checkBox.isChecked()) {
                    saveData.pushSetting = false;
                    checkBox.setChecked(false);
                } else {
                    saveData.pushSetting = true;
                    checkBox.setChecked(true);
                }
            }
        });

        ((Button)view.findViewById(R.id.howToUseButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        ((Button)view.findViewById(R.id.endedProjectButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((Button)view.findViewById(R.id.termButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView(Constants.WebPageUrl.terms, "利用規約");
            }
        });

        ((Button)view.findViewById(R.id.privacyPolicyButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView(Constants.WebPageUrl.privacypolicy, "個人情報保護方針");
            }
        });

        ((Button)view.findViewById(R.id.companyButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Constants.WebPageUrl.company);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }

    private void openWebView(String url, String title) {

        WebViewFragment fragment = new WebViewFragment();
        fragment.set(url, title);
        stackFragment(fragment, AnimationType.horizontal);
    }
}
