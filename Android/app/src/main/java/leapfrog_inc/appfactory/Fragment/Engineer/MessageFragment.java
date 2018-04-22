package leapfrog_inc.appfactory.Fragment.Engineer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/22.
 */

public class MessageFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_message, null);

        return view;
    }
}
