package leapfrog_inc.appfactory.Fragment.Splash;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.FragmentController;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.MainActivity;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/15.
 */

public class SplashFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_splash, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity mainActivity = (MainActivity)getActivity();
                if (mainActivity != null) {
                    mainActivity.getFragmentController().replace(new TabbarFragment(), FragmentController.AnimationType.none);
                }
            }
        }, 2000);

        return view;
    }
}
