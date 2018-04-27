package leapfrog_inc.appfactory.Fragment.Common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.MainActivity;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/25.
 */

public class Loading extends BaseFragment {

    public static Loading mFragment;

    public static void start(FragmentActivity activity) {

        if (mFragment != null) {
            return;
        }

        if (!(activity instanceof MainActivity)) {
            return;
        }

        MainActivity mainActivity = (MainActivity)activity;
        Loading loading = new Loading();
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(mainActivity.getSubContainerId(), loading);
        transaction.commitAllowingStateLoss();

        mFragment = loading;
    }

    public static void stop(FragmentActivity activity) {

        if (mFragment == null) {
            return;
        }

        if (!(activity instanceof MainActivity)) {
            return;
        }
        MainActivity mainActivity = (MainActivity)activity;
        FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.remove(mFragment);
        transaction.commitAllowingStateLoss();

        mFragment = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_loading, null);

        ImageView imageView1 = (ImageView)view.findViewById(R.id.loading1ImageView);
        RotateAnimation animation1 = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation1.setDuration(1200);
        animation1.setInterpolator(new LinearInterpolator());
        animation1.setRepeatCount(Animation.INFINITE);
        imageView1.startAnimation(animation1);

        ImageView imageView2 = (ImageView)view.findViewById(R.id.loading2ImageView);
        RotateAnimation animation2 = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation2.setDuration(1600);
        animation2.setInterpolator(new LinearInterpolator());
        animation2.setRepeatCount(Animation.INFINITE);
        imageView2.startAnimation(animation2);

        return view;
    }
}
