package leapfrog_inc.appfactory.Fragment.Tabbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Common.Loading;
import leapfrog_inc.appfactory.Fragment.Engineer.EngineerFragment;
import leapfrog_inc.appfactory.Fragment.Gallery.GalleryFragment;
import leapfrog_inc.appfactory.Fragment.Other.OtherFragment;
import leapfrog_inc.appfactory.Fragment.Progress.ProgressFragment;
import leapfrog_inc.appfactory.MainActivity;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class TabbarFragment extends BaseFragment {

    private EngineerFragment mEngineerFragment;
    private GalleryFragment mGalleryFragment;
    private ProgressFragment mProgressFragment;
    private OtherFragment mOtherFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_tabbar, null);

        initFragmentController();
        changeTab(0);
        initAction(view);

        ((MainActivity)getActivity()).mTabbarFragment = this;

        return view;
    }

    private void initFragmentController() {

        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int contentsLayoutId = R.id.contentsLayout;

        mEngineerFragment = new EngineerFragment();
        transaction.add(contentsLayoutId, mEngineerFragment);

        mGalleryFragment = new GalleryFragment();
        transaction.add(contentsLayoutId, mGalleryFragment);

        mProgressFragment = new ProgressFragment();
        transaction.add(contentsLayoutId, mProgressFragment);

        mOtherFragment = new OtherFragment();
        transaction.add(contentsLayoutId, mOtherFragment);

        transaction.commitAllowingStateLoss();
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.tab1Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(0);
            }
        });

        ((Button)view.findViewById(R.id.tab2Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(1);
            }
        });

        ((Button)view.findViewById(R.id.tab3Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(2);
            }
        });

        ((Button)view.findViewById(R.id.tab4Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTab(3);
            }
        });
    }

    private void changeTab(int index) {

        View view = getView();
        if (view == null) return;

        if (index == 0) {
            mEngineerFragment.getView().setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab1OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab1OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            mEngineerFragment.getView().setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab1OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab1OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 1) {
            mGalleryFragment.getView().setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab2OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab2OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            mGalleryFragment.getView().setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab2OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab2OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 2) {
            mProgressFragment.getView().setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab3OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab3OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            mProgressFragment.getView().setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab3OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab3OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 3) {
            mOtherFragment.getView().setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab4OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab4OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            mOtherFragment.getView().setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab4OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab4OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }
    }
}
