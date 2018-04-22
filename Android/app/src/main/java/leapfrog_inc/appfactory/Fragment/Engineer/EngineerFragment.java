package leapfrog_inc.appfactory.Fragment.Engineer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.Http.Enum.CostType;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.Enum.WorksType;
import leapfrog_inc.appfactory.Http.Requester.EngineerRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class EngineerFragment extends BaseFragment {

    private int mPage = 0;
    private int mTotalCount = 0;
    private ArrayList<EngineerRequester.EngineerData> mEngineerList = new ArrayList<EngineerRequester.EngineerData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer, null);

        initAction(view);
        refresh();

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabbarFragment tabbar = getTabbar();
                if (tabbar != null) {
                    EngineerMenuFragment fragment = new EngineerMenuFragment();
                    tabbar.stackFragment(fragment, AnimationType.none);
                }
            }
        });
    }

    private void refresh() {

        EngineerRequester.get(0, "", OrganizationType.unspecified, CostType.unspecified, WorksType.unspecified, new EngineerRequester.EngineerRequesterCallback() {
            @Override
            public void didReceiveData(EngineerRequester.EngineerResponseData response) {
                if (response != null) {
                    mPage = response.page;
                    mTotalCount = response.total;
                    mEngineerList = response.engineerList;
                    resetListView();

                } else {
                    // TODO
                }
            }
        });
    }

    private void resetListView() {

        View view = getView();
        if (view == null) return;
        ListView listView = (ListView)view.findViewById(R.id.listView);

        EngineerAdapter adapter = new EngineerAdapter(getActivity());

        for (int i = 0; i < mEngineerList.size(); i++) {
            adapter.add(mEngineerList.get(i));
        }

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EngineerRequester.EngineerData engineerData = (EngineerRequester.EngineerData) adapterView.getItemAtPosition(i);

                EngineerDetailFragment fragment = new EngineerDetailFragment();
                TabbarFragment tabbar = getTabbar();
                if (tabbar != null) {
                    tabbar.stackFragment(fragment, AnimationType.horizontal);
                }
            }
        });
    }



    public static class EngineerAdapter extends ArrayAdapter<EngineerRequester.EngineerData> {

        LayoutInflater mInflater;
        Context mContext;

        public EngineerAdapter(Context context){
            super(context, 0);
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = mInflater.inflate(R.layout.adapter_engineer, parent, false);

            EngineerRequester.EngineerData engineerData = getItem(position);

            return convertView;
        }
    }
}
