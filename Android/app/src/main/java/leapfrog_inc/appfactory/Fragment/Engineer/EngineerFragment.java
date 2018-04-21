package leapfrog_inc.appfactory.Fragment.Engineer;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.FragmentController;
import leapfrog_inc.appfactory.Fragment.Tabbar.Tab1Controller;
import leapfrog_inc.appfactory.MainActivity;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class EngineerFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EngineerMenuFragment fragment = new EngineerMenuFragment();

                MainActivity activity = (MainActivity)getActivity();
                int containerId = activity.getSubContainerId();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ft.add(containerId, fragment);
                ft.commit();
            }
        });
    }
}
