package leapfrog_inc.appfactory.Fragment.Gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Common.Dialog;
import leapfrog_inc.appfactory.Fragment.Common.Loading;
import leapfrog_inc.appfactory.Fragment.Engineer.EngineerDetailFragment;
import leapfrog_inc.appfactory.Fragment.Engineer.EngineerFragment;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Function.PicassoUtility;
import leapfrog_inc.appfactory.Http.Enum.GallerySortType;
import leapfrog_inc.appfactory.Http.Requester.EngineerDetailRequester;
import leapfrog_inc.appfactory.Http.Requester.EngineerRequester;
import leapfrog_inc.appfactory.Http.Requester.GalleryRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class GalleryFragment extends BaseFragment {

    private GallerySortType mSortType = GallerySortType.evaluate;
    private int mPage = 0;
    private int mTotalCount = 0;
    private boolean mIsLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_gallery, null);

        initListView(view);
        initAction(view);
        refresh();
        
        return view;
    }

    private void initListView(View view) {

        ListView listView = view.findViewById(R.id.listView);

        GalleryAdapter adapter = new GalleryAdapter(getActivity(), new GalleryAdapter.GalleryAdapterCallback() {
            @Override
            public void didSelect(String engineerId) {
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
        });
        listView.setAdapter(adapter);

        ((ListView)view.findViewById(R.id.listView)).setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

                if (mIsLoading) {
                    return;
                }
                View view = getView();
                if (view == null) return;

                ListView listView = view.findViewById(R.id.listView);
                if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1) {
                    if (mPage + 1 < mTotalCount) {
                        mPage += 1;
                        refresh();
                    }
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {}
        });
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.sortButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GallerySortFragment fragment = new GallerySortFragment();
                fragment.set(mSortType, new GallerySortFragment.GallerySortFragmentCallback() {
                    @Override
                    public void didSelect(GallerySortType sortType) {
                        View view = getView();
                        if (view == null) return;
                        ListView listView = (ListView)view.findViewById(R.id.listView);
                        GalleryAdapter adapter = (GalleryAdapter) listView.getAdapter();
                        adapter.clear();

                        mSortType = sortType;
                        mPage = 0;
                        mTotalCount = 0;
                        refresh();
                    }
                });
                TabbarFragment tabbar = getTabbar();
                tabbar.stackFragment(fragment, AnimationType.none);
            }
        });
    }

    private void refresh() {

        mIsLoading = true;
        Loading.start(getActivity());

        GalleryRequester.get(mSortType, mPage, new GalleryRequester.GalleryRequesterCallback() {
            @Override
            public void didReceiveData(boolean result, GalleryRequester.GalleryResponseData response) {
                mIsLoading = false;
                Loading.stop(getActivity());

                if (response != null) {
                    mPage = response.page;
                    mTotalCount = response.total;
                    appendListView(response.galleryList);
                } else {
                    // TODO
                }
            }
        });
    }

    private void appendListView(ArrayList<GalleryRequester.GalleryData> galleryList) {

        View view = getView();
        if (view == null) return;
        ListView listView = (ListView)view.findViewById(R.id.listView);

        GalleryAdapter adapter = (GalleryAdapter) listView.getAdapter();

        for (int i = 0; i < galleryList.size() / 2 + 1; i++) {
            ArrayList<GalleryRequester.GalleryData> addList = new ArrayList<GalleryRequester.GalleryData>();
            if (galleryList.size() <= i * 2) {
                continue;
            }
            if (galleryList.size() > i * 2) {
                addList.add(galleryList.get(i * 2));
            }
            if (galleryList.size() > i * 2 + 1) {
                addList.add(galleryList.get(i * 2 + 1));
            }
            adapter.add(addList);
        }

        adapter.notifyDataSetChanged();
    }

    public static class GalleryAdapter extends ArrayAdapter<ArrayList<GalleryRequester.GalleryData>> {

        LayoutInflater mInflater;
        Context mContext;
        GalleryAdapterCallback mCallback;

        public GalleryAdapter(Context context, GalleryAdapterCallback callback){
            super(context, 0);
            mInflater = LayoutInflater.from(context);
            mContext = context;
            mCallback = callback;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = mInflater.inflate(R.layout.adapter_gallery, parent, false);

            ArrayList<GalleryRequester.GalleryData> galleryList = getItem(position);
            if (galleryList.size() >= 1) {
                convertView.findViewById(R.id.gallery1Layout).setVisibility(View.VISIBLE);

                GalleryRequester.GalleryData galleryData = galleryList.get(0);

                String imageUrl = Constants.ServerWorkImageDirectory + galleryData.id;
                PicassoUtility.getWorkImage(mContext, imageUrl, (ImageView)convertView.findViewById(R.id.workImageView1));

                ((TextView)convertView.findViewById(R.id.nameTextView1)).setText(galleryData.name);
                ((TextView)convertView.findViewById(R.id.engineerTextView1)).setText(galleryData.engineerName);
                ((TextView)convertView.findViewById(R.id.areaTextView1)).setText(galleryData.engineerArea);
                ((TextView)convertView.findViewById(R.id.organizationTextView1)).setText(galleryData.engineerOrganization.toText());

                String cost = String.format("%,d円", galleryData.cost);
                ((TextView)convertView.findViewById(R.id.costTextView1)).setText(cost);

                String evaluateText = String.valueOf((int)(galleryData.evaluate / 10)) + "." + String.valueOf((int)(galleryData.evaluate % 10));
                ((TextView)convertView.findViewById(R.id.evaluateTextView1)).setText(evaluateText);

                final String engineerId = galleryData.engineerId;
                ((Button)convertView.findViewById(R.id.galleryButton1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.didSelect(engineerId);
                    }
                });

            } else {
                convertView.findViewById(R.id.gallery1Layout).setVisibility(View.GONE);
            }

            if (galleryList.size() >= 2) {
                convertView.findViewById(R.id.gallery2Layout).setVisibility(View.VISIBLE);

                final GalleryRequester.GalleryData galleryData = galleryList.get(1);

                String imageUrl = Constants.ServerWorkImageDirectory + galleryData.id;
                PicassoUtility.getWorkImage(mContext, imageUrl, (ImageView)convertView.findViewById(R.id.workImageView2));

                ((TextView)convertView.findViewById(R.id.nameTextView2)).setText(galleryData.name);
                ((TextView)convertView.findViewById(R.id.engineerTextView2)).setText(galleryData.engineerName);
                ((TextView)convertView.findViewById(R.id.areaTextView2)).setText(galleryData.engineerArea);
                ((TextView)convertView.findViewById(R.id.organizationTextView2)).setText(galleryData.engineerOrganization.toText());

                String cost = String.format("%,d円", galleryData.cost);
                ((TextView)convertView.findViewById(R.id.costTextView2)).setText(cost);

                String evaluateText = String.valueOf((int)(galleryData.evaluate / 10)) + "." + String.valueOf((int)(galleryData.evaluate % 10));
                ((TextView)convertView.findViewById(R.id.evaluateTextView2)).setText(evaluateText);

                final String engineerId = galleryData.engineerId;
                ((Button)convertView.findViewById(R.id.galleryButton2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.didSelect(engineerId);
                    }
                });
            } else {
                convertView.findViewById(R.id.gallery2Layout).setVisibility(View.GONE);
            }

            return convertView;
        }

        interface GalleryAdapterCallback {
            void didSelect(String engineerId);
        }
    }
}
