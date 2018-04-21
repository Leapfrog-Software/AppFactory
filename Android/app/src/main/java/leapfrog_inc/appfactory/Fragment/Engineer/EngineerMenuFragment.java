package leapfrog_inc.appfactory.Fragment.Engineer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Function.DeviceUtility;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/17.
 */

public class EngineerMenuFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer_menu, null);

        moveContents(view);



        return view;
    }

    private void moveContents(View view) {

        int fromX = -250 * (int)DeviceUtility.getDeviceDensity(getActivity());
        TranslateAnimation animation = new TranslateAnimation(fromX, 0, 0, 0);
        animation.setDuration(200);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        ScrollView scrollView = (ScrollView)view.findViewById(R.id.scrollView);
        scrollView.startAnimation(animation);
    }
}
