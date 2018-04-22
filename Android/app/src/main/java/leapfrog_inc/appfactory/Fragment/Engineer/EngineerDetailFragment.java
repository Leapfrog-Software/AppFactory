package leapfrog_inc.appfactory.Fragment.Engineer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Tabbar.TabbarFragment;
import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Function.PicassoUtility;
import leapfrog_inc.appfactory.Http.Requester.EngineerDetailRequester;
import leapfrog_inc.appfactory.Http.Requester.EngineerRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/22.
 */

public class EngineerDetailFragment extends BaseFragment {

    private EngineerDetailRequester.EngineerDetailResponseData mEngineerDetailData;

    public void set(EngineerDetailRequester.EngineerDetailResponseData engineerDetailData) {
        mEngineerDetailData = engineerDetailData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer_detail, null);

        initListView(view);
        initAction(view);

        return view;
    }

    private void initListView(View view) {

        ListView listView = view.findViewById(R.id.listView);

        EngineerDetailAdapter adapter = new EngineerDetailAdapter(getActivity());

        EngineerDetailAdapterData profileData = new EngineerDetailAdapterData();
        profileData.engineerDetailData = mEngineerDetailData;
        adapter.add(profileData);

        for (int i = 0; i < mEngineerDetailData.works.size(); i++) {
            EngineerDetailAdapterData workData = new EngineerDetailAdapterData();
            workData.workData = mEngineerDetailData.works.get(i);
            adapter.add(workData);
        }

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment(AnimationType.horizontal);
            }
        });
    }

    static class EngineerDetailAdapterData {
        EngineerDetailRequester.EngineerDetailResponseData engineerDetailData;
        EngineerDetailRequester.EngineerDetailWorkData workData;
    }

    public static class EngineerDetailAdapter extends ArrayAdapter<EngineerDetailAdapterData> {

        LayoutInflater mInflater;
        Context mContext;

        public EngineerDetailAdapter(Context context){
            super(context, 0);
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            EngineerDetailAdapterData adapterData = getItem(position);

            if (adapterData.engineerDetailData != null) {
                convertView = mInflater.inflate(R.layout.adapter_engineer_detail_profile, parent, false);

                EngineerDetailRequester.EngineerDetailResponseData detailData = adapterData.engineerDetailData;

                String imageUrl = Constants.ServerEngineerImageDirectory + detailData.id;
                PicassoUtility.getEngineerImage(mContext, imageUrl, (ImageView)convertView.findViewById(R.id.engineerImageView));

                ((TextView)convertView.findViewById(R.id.nameTextView)).setText(detailData.name);
                ((TextView)convertView.findViewById(R.id.areaTextView)).setText(detailData.area);
                ((TextView)convertView.findViewById(R.id.organizationTextView)).setText(detailData.organization.toText());

                int[] emptyImageViewIds = {R.id.star1EmptyImageView, R.id.star2EmptyImageView, R.id.star3EmptyImageView, R.id.star4EmptyImageView, R.id.star5EmptyImageView};
                int[] halfImageViewIds = {R.id.star1HalfImageView, R.id.star2HalfImageView, R.id.star3HalfImageView, R.id.star4HalfImageView, R.id.star5HalfImageView};
                int[] fullImageViewIds = {R.id.star1FullImageView, R.id.star2FullImageView, R.id.star3FullImageView, R.id.star4FullImageView, R.id.star5FullImageView};

                for (int i = 0; i < 5; i++) {
                    ImageView emptyImageView = convertView.findViewById(emptyImageViewIds[i]);
                    ImageView halfImageView = convertView.findViewById(halfImageViewIds[i]);
                    ImageView fullImageView = convertView.findViewById(fullImageViewIds[i]);

                    if (detailData.evaluate < i * 10 + 5) {
                        emptyImageView.setVisibility(View.VISIBLE);
                        halfImageView.setVisibility(View.GONE);
                        fullImageView.setVisibility(View.GONE);
                    } else if (detailData.evaluate < i * 10 + 10) {
                        emptyImageView.setVisibility(View.GONE);
                        halfImageView.setVisibility(View.VISIBLE);
                        fullImageView.setVisibility(View.GONE);
                    } else {
                        emptyImageView.setVisibility(View.GONE);
                        halfImageView.setVisibility(View.GONE);
                        fullImageView.setVisibility(View.VISIBLE);
                    }
                }

                String evaluateText = String.valueOf((int)(detailData.evaluate / 10)) + "." + String.valueOf((int)(detailData.evaluate % 10));
                ((TextView)convertView.findViewById(R.id.evaluateTextView)).setText(evaluateText);

                ((TextView)convertView.findViewById(R.id.profileTextView)).setText(detailData.profile);

                String workCount = String.format("(%d件)", detailData.works.size());
                ((TextView)convertView.findViewById(R.id.workCountTextView)).setText(workCount);

            } else if (adapterData.workData != null) {
                convertView = mInflater.inflate(R.layout.adapter_engineer_detail_work, parent, false);

                EngineerDetailRequester.EngineerDetailWorkData workData = adapterData.workData;

                String imageurl = Constants.ServerWorkImageDirectory + workData.id;
                PicassoUtility.getWorkImage(mContext, imageurl, (ImageView)convertView.findViewById(R.id.workImageView));

                ((TextView)convertView.findViewById(R.id.nameTextView)).setText(workData.name);
                ((TextView)convertView.findViewById(R.id.osTextView)).setText(workData.os);
                ((TextView)convertView.findViewById(R.id.functionTextView)).setText(workData.function);

                String cost = String.format("%,d円", workData.cost);
                ((TextView)convertView.findViewById(R.id.costTextView)).setText(cost);

                int[] emptyImageViewIds = {R.id.star1EmptyImageView, R.id.star2EmptyImageView, R.id.star3EmptyImageView, R.id.star4EmptyImageView, R.id.star5EmptyImageView};
                int[] halfImageViewIds = {R.id.star1HalfImageView, R.id.star2HalfImageView, R.id.star3HalfImageView, R.id.star4HalfImageView, R.id.star5HalfImageView};
                int[] fullImageViewIds = {R.id.star1FullImageView, R.id.star2FullImageView, R.id.star3FullImageView, R.id.star4FullImageView, R.id.star5FullImageView};

                for (int i = 0; i < 5; i++) {
                    ImageView emptyImageView = convertView.findViewById(emptyImageViewIds[i]);
                    ImageView halfImageView = convertView.findViewById(halfImageViewIds[i]);
                    ImageView fullImageView = convertView.findViewById(fullImageViewIds[i]);

                    if (workData.evaluate < i * 10 + 5) {
                        emptyImageView.setVisibility(View.VISIBLE);
                        halfImageView.setVisibility(View.GONE);
                        fullImageView.setVisibility(View.GONE);
                    } else if (workData.evaluate < i * 10 + 10) {
                        emptyImageView.setVisibility(View.GONE);
                        halfImageView.setVisibility(View.VISIBLE);
                        fullImageView.setVisibility(View.GONE);
                    } else {
                        emptyImageView.setVisibility(View.GONE);
                        halfImageView.setVisibility(View.GONE);
                        fullImageView.setVisibility(View.VISIBLE);
                    }
                }
            }

            return convertView;
        }
    }
}
