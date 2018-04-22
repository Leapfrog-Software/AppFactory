package leapfrog_inc.appfactory.Fragment.Engineer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Function.PicassoUtility;
import leapfrog_inc.appfactory.Http.Enum.CostType;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.Enum.WorksType;
import leapfrog_inc.appfactory.Http.Requester.EngineerDetailRequester;
import leapfrog_inc.appfactory.Http.Requester.EngineerRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class EngineerFragment extends BaseFragment {

    private int mPage = 0;
    private int mTotalCount = 0;
    private ArrayList<EngineerRequester.EngineerData> mEngineerList = new ArrayList<EngineerRequester.EngineerData>();
    private boolean mIsLoading = false;

    private String mSearchWord = "";
    private OrganizationType mOrganizationType = OrganizationType.unspecified;
    private CostType mCostType = CostType.unspecified;
    private WorksType mWorksType = WorksType.unspecified;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer, null);

        initListView(view);
        initAction(view);
        refresh();

        return view;
    }

    private void initListView(View view) {

        ListView listView = view.findViewById(R.id.listView);

        EngineerAdapter adapter = new EngineerAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EngineerRequester.EngineerData engineerData = (EngineerRequester.EngineerData) adapterView.getItemAtPosition(i);
                fetchDetailData(engineerData.id);
            }
        });
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabbarFragment tabbar = getTabbar();
                if (tabbar != null) {
                    EngineerMenuFragment fragment = new EngineerMenuFragment();
                    fragment.set(mSearchWord, mOrganizationType, mCostType, mWorksType, new EngineerMenuFragment.EngineerMenuFragmentCallback() {
                        @Override
                        public void onSearch(String word, OrganizationType organization, CostType costType, WorksType worksType) {
                            mSearchWord = word;
                            mOrganizationType = organization;
                            mCostType = costType;
                            mWorksType = worksType;
                            mPage = 0;
                            mTotalCount = 0;
                            mEngineerList.clear();
                            refresh();

                            View view = getView();
                            if (view == null) return;
                            initListView(view);
                        }
                    });
                    tabbar.stackFragment(fragment, AnimationType.none);
                }
            }
        });

        ((ListView)view.findViewById(R.id.listView)).setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                View view = getView();
                if (view == null) return;
                ListView listView = view.findViewById(R.id.listView);
                if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1) {
                    if (mPage < mTotalCount / 20 + 1) {
                        mPage += 1;
                        refresh();
                    }
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {}
        });
    }

    private void refresh() {

        mIsLoading = true;

        EngineerRequester.get(mPage, mSearchWord, mOrganizationType, mCostType, mWorksType, new EngineerRequester.EngineerRequesterCallback() {
            @Override
            public void didReceiveData(EngineerRequester.EngineerResponseData response) {

                mIsLoading = false;

                if (response != null) {
                    mPage = response.page;
                    mTotalCount = response.total;
                    for (int i = 0; i < response.engineerList.size(); i++) {
                        mEngineerList.add(response.engineerList.get(i));
                    }
                    appendListView(response.engineerList);

                } else {
                    // TODO
                }
            }
        });
    }

    private void appendListView(ArrayList<EngineerRequester.EngineerData> engineerList) {

        View view = getView();
        if (view == null) return;
        ListView listView = (ListView)view.findViewById(R.id.listView);

        EngineerAdapter adapter = (EngineerAdapter) listView.getAdapter();
//        ListAdapter oldAdapter = listView.getAdapter();
//        if ((oldAdapter != null) && (oldAdapter instanceof EngineerAdapter)) {
//            adapter = (EngineerAdapter) oldAdapter;
//        } else {
//            adapter = new EngineerAdapter(getActivity());
//        }

        for (int i = 0; i < engineerList.size(); i++) {
            adapter.add(engineerList.get(i));
        }

        adapter.notifyDataSetChanged();
    }

    private void fetchDetailData(String engineerId) {

        EngineerDetailRequester.get(engineerId, new EngineerDetailRequester.EngineerDetailRequesterCallback() {
            @Override
            public void didReceiveData(EngineerDetailRequester.EngineerDetailResponseData response) {
                if (response != null) {
                    EngineerDetailFragment fragment = new EngineerDetailFragment();
                    fragment.set(response);
                    TabbarFragment tabbar = getTabbar();
                    if (tabbar != null) {
                        tabbar.stackFragment(fragment, AnimationType.horizontal);
                    }
                } else {
                    // TODO
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

            String imageUrl = Constants.ServerEngineerImageDirectory + engineerData.id + ".png";
            PicassoUtility.getEngineerImage(mContext, imageUrl, (ImageView)convertView.findViewById(R.id.engineerImageView));

            ((TextView)convertView.findViewById(R.id.nameTextView)).setText(engineerData.name);
            ((TextView)convertView.findViewById(R.id.areaTextView)).setText(engineerData.area);
            ((TextView)convertView.findViewById(R.id.oranizationTextView)).setText(engineerData.organization.toText());
            ((TextView)convertView.findViewById(R.id.profileTextView)).setText(engineerData.profile);

            int[] emptyImageViewIds = {R.id.star1EmptyImageView, R.id.star2EmptyImageView, R.id.star3EmptyImageView, R.id.star4EmptyImageView, R.id.star5EmptyImageView};
            int[] halfImageViewIds = {R.id.star1HalfImageView, R.id.star2HalfImageView, R.id.star3HalfImageView, R.id.star4HalfImageView, R.id.star5HalfImageView};
            int[] fullImageViewIds = {R.id.star1FullImageView, R.id.star2FullImageView, R.id.star3FullImageView, R.id.star4FullImageView, R.id.star5FullImageView};

            for (int i = 0; i < 5; i++) {
                ImageView emptyImageView = convertView.findViewById(emptyImageViewIds[i]);
                ImageView halfImageView = convertView.findViewById(halfImageViewIds[i]);
                ImageView fullImageView = convertView.findViewById(fullImageViewIds[i]);

                if (engineerData.evaluate < i * 10 + 5) {
                    emptyImageView.setVisibility(View.VISIBLE);
                    halfImageView.setVisibility(View.GONE);
                    fullImageView.setVisibility(View.GONE);
                } else if (engineerData.evaluate < i * 10 + 10) {
                    emptyImageView.setVisibility(View.GONE);
                    halfImageView.setVisibility(View.VISIBLE);
                    fullImageView.setVisibility(View.GONE);
                } else {
                    emptyImageView.setVisibility(View.GONE);
                    halfImageView.setVisibility(View.GONE);
                    fullImageView.setVisibility(View.VISIBLE);
                }
            }

            return convertView;
        }
    }
}
