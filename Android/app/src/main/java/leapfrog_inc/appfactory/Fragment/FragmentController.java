package leapfrog_inc.appfactory.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/15.
 */

public class FragmentController {

    public enum AnimationType {
        vertical,
        horizontal,
        none
    }

    private FragmentManager mFragmentManager = null;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private int mContainerId = 0;

    public void initialize(FragmentManager fm, int containerId) {
        mFragmentManager = fm;
        mContainerId = containerId;
    }

    public void stack(BaseFragment fragment, AnimationType animationType) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(animationType == AnimationType.horizontal){
            ft.setCustomAnimations(R.anim.stack_from_right, R.anim.close_for_left);
        } else if (animationType == AnimationType.vertical) {
            ft.setCustomAnimations(R.anim.stack_from_bottom, R.anim.close_for_top);
        }
        for(int i = 0; i < mFragmentList.size() ;i++) {
            ft.remove(mFragmentList.get(i));
        }
        ft.add(mContainerId, fragment);
        ft.commitAllowingStateLoss();
        mFragmentList.add(fragment);
    }

    public void pop(AnimationType animationType) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(animationType == AnimationType.horizontal){
            ft.setCustomAnimations(R.anim.stack_from_left, R.anim.close_for_right);
        } else if (animationType == AnimationType.vertical) {
            ft.setCustomAnimations(R.anim.stack_from_top, R.anim.close_for_bottom);
        }
        ft.remove(mFragmentList.get(mFragmentList.size() - 1));
        ft.add(mContainerId, mFragmentList.get(mFragmentList.size() - 2));
        ft.commit();

        mFragmentList.remove(mFragmentList.size() - 1);
    }

    public void replace(BaseFragment fragment, AnimationType animationType) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(animationType == AnimationType.horizontal){
            ft.setCustomAnimations(R.anim.stack_from_right, R.anim.close_for_left);
        } else if (animationType == AnimationType.vertical) {
            ft.setCustomAnimations(R.anim.stack_from_bottom, R.anim.close_for_top);
        }
        for(int i = mFragmentList.size() - 1; i >= 0; i--){
            ft.remove(mFragmentList.get(i));
            mFragmentList.remove(i);
        }
        ft.add(mContainerId, fragment);
        ft.commitAllowingStateLoss();
        mFragmentList.add(fragment);
    }

    public void show(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.show(mFragmentList.get(mFragmentList.size() - 1));
        ft.commit();
    }

    public void hide(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.hide(mFragmentList.get(mFragmentList.size() - 1));
        ft.commit();
    }
}
