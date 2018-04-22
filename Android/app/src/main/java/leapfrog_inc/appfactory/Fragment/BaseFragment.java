package leapfrog_inc.appfactory.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.Function.DeviceUtility;
import leapfrog_inc.appfactory.MainActivity;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/15.
 */

public class BaseFragment extends Fragment {

    public enum AnimationType {
        none,
        horizontal,
        vertical
    }

    private AnimationType mAnimationType = AnimationType.none;

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(params);
        }

        if (mAnimationType != AnimationType.none) {
            int fromXDelta = (mAnimationType == AnimationType.horizontal) ? (int)(-DeviceUtility.getWindowSize(getActivity()).x) : 0;
            int fromYdelta = (mAnimationType == AnimationType.horizontal) ? 0 : (int)(-DeviceUtility.getWindowSize(getActivity()).y);
            TranslateAnimation animation = new TranslateAnimation(fromXDelta, 0, fromYdelta, 0);
            animation.setDuration(200);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
    }

    public void stackFragment(BaseFragment fragment, AnimationType animationType) {

        fragment.mAnimationType = animationType;

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.rootLayout, fragment);
        transaction.commitAllowingStateLoss();
    }

    public TabbarFragment getTabbar() {

        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            return ((MainActivity)activity).mTabbarFragment;
        }
        return null;
    }
}
