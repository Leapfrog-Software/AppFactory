package leapfrog_inc.appfactory.Fragment.Tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Function.DeviceUtility;
import leapfrog_inc.appfactory.Function.SaveData;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/28.
 */

public class TutorialFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_tutorial, null);

        initContent(view);
        initAction(view);

        return view;
    }

    private void initContent(View view) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        TutorialPager adapter = new TutorialPager(manager);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                moveStartButton(position, positionOffset);
            }
            @Override
            public void onPageSelected(int position) {
                changeProgress(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        changeProgress(0);
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.startButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData saveData = SaveData.getInstance();
                saveData.didInitialized = true;
                saveData.save();

                popFragment(AnimationType.none);
            }
        });
    }

    private void changeProgress(int index) {

        View view = getView();
        if (view == null) return;

        if (index == 0) {
            view.findViewById(R.id.progress1Active).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progress1Passive).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.progress1Active).setVisibility(View.GONE);
            view.findViewById(R.id.progress1Passive).setVisibility(View.VISIBLE);
        }
        if (index == 1) {
            view.findViewById(R.id.progress2Active).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progress2Passive).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.progress2Active).setVisibility(View.GONE);
            view.findViewById(R.id.progress2Passive).setVisibility(View.VISIBLE);
        }
        if (index == 2) {
            view.findViewById(R.id.progress3Active).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progress3Passive).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.progress3Active).setVisibility(View.GONE);
            view.findViewById(R.id.progress3Passive).setVisibility(View.VISIBLE);
        }
        if (index == 3) {
            view.findViewById(R.id.progress4Active).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progress4Passive).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.progress4Active).setVisibility(View.GONE);
            view.findViewById(R.id.progress4Passive).setVisibility(View.VISIBLE);
        }
        if (index == 4) {
            view.findViewById(R.id.progress5Active).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progress5Passive).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.progress5Active).setVisibility(View.GONE);
            view.findViewById(R.id.progress5Passive).setVisibility(View.VISIBLE);
        }
    }

    private void moveStartButton(int position, float offset) {

        View view = getView();
        if (view == null) return;

        Button startButton = (Button)view.findViewById(R.id.startButton);

        float density = DeviceUtility.getDeviceDensity(getActivity());

        int bottomMargin;
        float startPosition = -density * 75;

        if (position == 4) {
            bottomMargin = 0;
        } else if (position == 3) {
            bottomMargin = (int)(startPosition * (1 - offset));
        } else {
            bottomMargin = (int)startPosition;
        }

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)startButton.getLayoutParams();
        params.bottomMargin = bottomMargin;
        startButton.setLayoutParams(params);
    }

    static class TutorialPager extends FragmentPagerAdapter {

        public TutorialPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            TutorialPagerFragment fragment = new TutorialPagerFragment();
            fragment.setTutorialIndex(position);
            return fragment;
        }
    }
}
