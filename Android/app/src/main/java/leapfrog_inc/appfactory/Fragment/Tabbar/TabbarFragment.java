package leapfrog_inc.appfactory.Fragment.Tabbar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Engineer.EngineerFragment;
import leapfrog_inc.appfactory.Fragment.FragmentController;
import leapfrog_inc.appfactory.Fragment.Gallery.GalleryFragment;
import leapfrog_inc.appfactory.Fragment.Other.OtherFragment;
import leapfrog_inc.appfactory.Fragment.Progress.ProgressFragment;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class TabbarFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_tabbar, null);

        initFragmentController();
        changeTab(0);

        return view;
    }

    private void initFragmentController() {

        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        int contentsLayoutId = R.id.contentsLayout;

        Tab1Controller.getInstance().initialize(fragmentManager, contentsLayoutId);
        Tab1Controller.getInstance().stack(new EngineerFragment(), FragmentController.AnimationType.none);

        Tab2Controller.getInstance().initialize(fragmentManager, contentsLayoutId);
        Tab2Controller.getInstance().stack(new GalleryFragment(), FragmentController.AnimationType.none);
        Tab2Controller.getInstance().hide();

        Tab3Controller.getInstance().initialize(fragmentManager, contentsLayoutId);
        Tab3Controller.getInstance().stack(new ProgressFragment(), FragmentController.AnimationType.none);
        Tab3Controller.getInstance().hide();

        Tab4Controller.getInstance().initialize(fragmentManager, contentsLayoutId);
        Tab4Controller.getInstance().stack(new OtherFragment(), FragmentController.AnimationType.none);
        Tab4Controller.getInstance().hide();
    }

    private void changeTab(int index) {

        View view = getView();
        if (view == null) return;

        if (index == 0) {
            Tab1Controller.getInstance().show();
            ((ImageView)view.findViewById(R.id.tab1OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab1OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            Tab1Controller.getInstance().hide();
            ((ImageView)view.findViewById(R.id.tab1OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab1OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 1) {
            Tab2Controller.getInstance().show();
            ((ImageView)view.findViewById(R.id.tab2OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab2OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            Tab2Controller.getInstance().hide();
            ((ImageView)view.findViewById(R.id.tab2OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab2OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 2) {
            Tab3Controller.getInstance().show();
            ((ImageView)view.findViewById(R.id.tab3OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab3OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            Tab3Controller.getInstance().hide();
            ((ImageView)view.findViewById(R.id.tab3OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab3OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }

        if (index == 3) {
            Tab4Controller.getInstance().show();
            ((ImageView)view.findViewById(R.id.tab4OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.tab4OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tab4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabSelected));
        } else {
            Tab4Controller.getInstance().hide();
            ((ImageView)view.findViewById(R.id.tab4OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.tab4OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tab4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.tabUnselected));
        }
    }
}
