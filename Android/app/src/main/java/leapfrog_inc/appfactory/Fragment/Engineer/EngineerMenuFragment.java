package leapfrog_inc.appfactory.Fragment.Engineer;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Function.DeviceUtility;
import leapfrog_inc.appfactory.Http.Enum.CostType;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.Enum.WorksType;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/17.
 */

public class EngineerMenuFragment extends BaseFragment {

    private String mDefaultWord;
    private OrganizationType mOrganizationType;
    private CostType mCostType;
    private WorksType mWorksType;
    private EngineerMenuFragmentCallback mCallback;

    public void set(String word, OrganizationType organizationType, CostType costType, WorksType worksType, EngineerMenuFragmentCallback callback) {
        mDefaultWord = word;
        mOrganizationType = organizationType;
        mCostType = costType;
        mWorksType = worksType;
        mCallback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_engineer_menu, null);

        moveContents(view);
        resetContents(view);
        initAction(view);

        return view;
    }

    private void moveContents(View view) {

        int fromX = (int)(-DeviceUtility.getWindowSize(getActivity()).x * 3 / 4);
        TranslateAnimation animation = new TranslateAnimation(fromX, 0, 0, 0);
        animation.setDuration(200);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        FrameLayout moveLayout = (FrameLayout)view.findViewById(R.id.moveLayout);
        moveLayout.startAnimation(animation);
    }

    private void resetContents(View v) {

        View view = v;
        if (view == null) view = getView();
        if (view == null) return;

        ((EditText)view.findViewById(R.id.wordEditText)).setText(mDefaultWord);

        if (mOrganizationType == OrganizationType.unspecified) {
            ((ImageView)view.findViewById(R.id.organization1OnImageVIew)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.organization1OffImageVIew)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.organization1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.organization1OnImageVIew)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.organization1OffImageVIew)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.organization1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mOrganizationType == OrganizationType.corporation) {
            ((ImageView)view.findViewById(R.id.organization2OnImageVIew)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.organization2OffImageVIew)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.organization2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.organization2OnImageVIew)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.organization2OffImageVIew)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.organization2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mOrganizationType == OrganizationType.indivisual) {
            ((ImageView)view.findViewById(R.id.organization3OnImageVIew)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.organization3OffImageVIew)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.organization3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.organization3OnImageVIew)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.organization3OffImageVIew)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.organization3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }

        if (mCostType == CostType.unspecified) {
            ((ImageView)view.findViewById(R.id.cost1OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.cost1OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.cost1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.cost1OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.cost1OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.cost1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mCostType == CostType.u100000) {
            ((ImageView)view.findViewById(R.id.cost2OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.cost2OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.cost2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.cost2OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.cost2OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.cost2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mCostType == CostType.u300000) {
            ((ImageView)view.findViewById(R.id.cost3OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.cost3OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.cost3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.cost3OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.cost3OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.cost3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mCostType == CostType.u1000000) {
            ((ImageView)view.findViewById(R.id.cost4OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.cost4OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.cost4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.cost4OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.cost4OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.cost4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mCostType == CostType.o10000000) {
            ((ImageView)view.findViewById(R.id.cost5OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.cost5OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.cost5TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.cost5OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.cost5OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.cost5TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }

        if (mWorksType == WorksType.unspecified) {
            ((ImageView)view.findViewById(R.id.works1OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works1OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works1OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works1OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works1TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mWorksType == WorksType.o10) {
            ((ImageView)view.findViewById(R.id.works2OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works2OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works2OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works2OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works2TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mWorksType == WorksType.o20) {
            ((ImageView)view.findViewById(R.id.works3OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works3OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works3OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works3OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works3TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mWorksType == WorksType.o30) {
            ((ImageView)view.findViewById(R.id.works4OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works4OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works4OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works4OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works4TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mWorksType == WorksType.o40) {
            ((ImageView)view.findViewById(R.id.works5OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works5OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works5TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works5OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works5OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works5TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
        if (mWorksType == WorksType.o50) {
            ((ImageView)view.findViewById(R.id.works6OnImageView)).setVisibility(View.VISIBLE);
            ((ImageView)view.findViewById(R.id.works6OffImageView)).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.works6TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.green));
        } else {
            ((ImageView)view.findViewById(R.id.works6OnImageView)).setVisibility(View.GONE);
            ((ImageView)view.findViewById(R.id.works6OffImageView)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.works6TextView)).setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.engineerMenuText));
        }
    }

    private void initAction(View view) {

        int organizationButtonIds[] = {R.id.organization1Button, R.id.organization2Button, R.id.organization3Button};
        for (int i = 0; i < organizationButtonIds.length; i++) {
            Button organizationButton = (Button)view.findViewById(organizationButtonIds[i]);
            organizationButton.setTag(i);
            organizationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int)view.getTag();
                    if (index == 0)     mOrganizationType = OrganizationType.unspecified;
                    else if (index == 1)    mOrganizationType = OrganizationType.corporation;
                    else                    mOrganizationType = OrganizationType.indivisual;
                    resetContents(null);
                }
            });
        }

        int costButtonIds[] = {R.id.cost1Button, R.id.cost2Button, R.id.cost3Button, R.id.cost4Button, R.id.cost5Button};
        for (int i = 0; i < costButtonIds.length; i++) {
            Button costButton = (Button)view.findViewById(costButtonIds[i]);
            costButton.setTag(i);
            costButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int)view.getTag();
                    if (index == 0)         mCostType = CostType.unspecified;
                    else if (index == 1)    mCostType = CostType.u100000;
                    else if (index == 2)    mCostType = CostType.u300000;
                    else if (index == 3)    mCostType = CostType.u1000000;
                    else                    mCostType = CostType.o10000000;
                    resetContents(null);
                }
            });
        }

        int worksButtonIds[] = {R.id.works1Button, R.id.works2Button, R.id.works3Button, R.id.works4Button, R.id.works5Button, R.id.works6Button};
        for (int i = 0; i < worksButtonIds.length; i++) {
            Button workButton = (Button)view.findViewById(worksButtonIds[i]);
            workButton.setTag(i);
            workButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int)view.getTag();
                    if (index == 0)         mWorksType = WorksType.unspecified;
                    else if (index == 1)    mWorksType = WorksType.o10;
                    else if (index == 2)    mWorksType = WorksType.o20;
                    else if (index == 3)    mWorksType = WorksType.o30;
                    else if (index == 4)    mWorksType = WorksType.o40;
                    else                    mWorksType = WorksType.o50;
                    resetContents(null);
                }
            });
        }

        ((Button)view.findViewById(R.id.searchButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSearch();
            }
        });

        ((Button)view.findViewById(R.id.closeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
    }

    private void onClickSearch() {

        View view = getView();
        if (view == null) return;

        String word = ((EditText)view.findViewById(R.id.wordEditText)).getText().toString();

        mCallback.onSearch(word, mOrganizationType, mCostType, mWorksType);
        close();
    }

    private void close() {

        View view = getView();
        if (view == null) return;

        int toX = (int)(-DeviceUtility.getWindowSize(getActivity()).x * 3 / 4);
        TranslateAnimation animation = new TranslateAnimation(0, toX, 0, 0);
        animation.setDuration(200);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                popFragment(AnimationType.none);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        FrameLayout moveLayout = (FrameLayout)view.findViewById(R.id.moveLayout);
        moveLayout.startAnimation(animation);
    }

    interface EngineerMenuFragmentCallback {
        void onSearch(String word, OrganizationType organization, CostType costType, WorksType worksType);
    }
}
