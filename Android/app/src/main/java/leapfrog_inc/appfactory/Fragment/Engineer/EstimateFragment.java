package leapfrog_inc.appfactory.Fragment.Engineer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Fragment.Common.Dialog;
import leapfrog_inc.appfactory.Fragment.Common.Loading;
import leapfrog_inc.appfactory.Fragment.Common.WebViewFragment;
import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Function.PicassoUtility;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.Requester.EngineerDetailRequester;
import leapfrog_inc.appfactory.Http.Requester.EstimateRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/22.
 */

public class EstimateFragment extends BaseFragment {

    private EngineerDetailRequester.EngineerDetailResponseData mEngineerDetailData;
    private boolean mIos = false;
    private boolean mAndroid = false;
    private boolean mChat = false;
    private boolean mCamera = false;
    private boolean mMovie = false;
    private boolean mPush = false;
    private boolean mMap = false;
    private boolean mGeofence = false;
    private boolean mSettlement = false;
    private boolean mCreditCard = false;
    private boolean mUser = false;
    private boolean mSns = false;
    private boolean mIsAgree = false;

    public void set(EngineerDetailRequester.EngineerDetailResponseData engineerDetailData) {
        mEngineerDetailData = engineerDetailData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_estimate, null);

        initContents(view);
        initAction(view);

        return view;
    }

    private void initContents(View view) {

        if (mEngineerDetailData == null) {
            String title = "登録中の開発者様に見積もり依頼を送信します";
            ((TextView) view.findViewById(R.id.titleTextView)).setText(title);
        } else {
            String title = mEngineerDetailData.name + "様に見積もり依頼を送信します";
            ((TextView) view.findViewById(R.id.titleTextView)).setText(title);
        }

        resetContents(view);
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment(AnimationType.horizontal);
            }
        });

        int platformButtonIds[] = {R.id.platform1Button, R.id.platform2Button};
        for (int i = 0; i < platformButtonIds.length; i++) {
            Button button = (Button)view.findViewById(platformButtonIds[i]);
            button.setTag(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int)view.getTag();
                    if (index == 0)     mIos = !mIos;
                    else                mAndroid = !mAndroid;
                    resetContents(null);
                }
            });
        }

        int functionButtonIds[] = {R.id.function1Button,
                                    R.id.function2Button,
                                    R.id.function3Button,
                                    R.id.function4Button,
                                    R.id.function5Button,
                                    R.id.function6Button,
                                    R.id.function7Button,
                                    R.id.function8Button,
                                    R.id.function9Button,
                                    R.id.function10Button};
        for (int i = 0; i < functionButtonIds.length; i++) {
            Button button = (Button)view.findViewById(functionButtonIds[i]);
            button.setTag(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int)view.getTag();
                    if (index == 0)         mChat = !mChat;
                    else if (index == 1)    mCamera = !mCamera;
                    else if (index == 2)    mMovie = !mMovie;
                    else if (index == 3)    mPush = !mPush;
                    else if (index == 4)    mMap = !mMap;
                    else if (index == 5)    mGeofence = !mGeofence;
                    else if (index == 6)    mSettlement = !mSettlement;
                    else if (index == 7)    mCreditCard = !mCreditCard;
                    else if (index == 8)    mUser = !mUser;
                    else                    mSns = !mSns;
                    resetContents(null);
                }
            });
        }

        ((Button)view.findViewById(R.id.agreeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsAgree = !mIsAgree;

                View view = getView();
                if (view == null) return;
                ((ImageView)view.findViewById(R.id.agreeOnImageView)).setVisibility(mIsAgree ? View.VISIBLE : View.GONE);
                ((ImageView)view.findViewById(R.id.agreeOffImageView)).setVisibility(mIsAgree ? View.GONE : View.VISIBLE);
            }
        });

        ((Button)view.findViewById(R.id.termButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = new WebViewFragment();
                fragment.set(Constants.WebPageUrl.terms, "利用規約");
                stackFragment(fragment, AnimationType.horizontal);
            }
        });

        ((Button)view.findViewById(R.id.sendButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSend();
            }
        });
    }

    private void resetContents(View v) {

        View view = v;
        if (view == null) view = getView();
        if (view == null) return;

        view.findViewById(R.id.platform1OnImageView).setVisibility(mIos ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.platform1OffImageView).setVisibility(mIos ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.platform2OnImageView).setVisibility(mAndroid ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.platform2OffImageView).setVisibility(mAndroid ? View.GONE : View.VISIBLE);

        view.findViewById(R.id.function1OnImageView).setVisibility(mChat ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function1OffImageView).setVisibility(mChat ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function2OnImageView).setVisibility(mCamera ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function2OffImageView).setVisibility(mCamera ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function3OnImageView).setVisibility(mMovie ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function3OffImageView).setVisibility(mMovie ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function4OnImageView).setVisibility(mPush ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function4OffImageView).setVisibility(mPush ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function5OnImageView).setVisibility(mMap ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function5OffImageView).setVisibility(mMap ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function6OnImageView).setVisibility(mGeofence ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function6OffImageView).setVisibility(mGeofence ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function7OnImageView).setVisibility(mSettlement ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function7OffImageView).setVisibility(mSettlement ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function8OnImageView).setVisibility(mCreditCard ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function8OffImageView).setVisibility(mCreditCard ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function9OnImageView).setVisibility(mUser ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function9OffImageView).setVisibility(mUser ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.function10OnImageView).setVisibility(mSns ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.function10OffImageView).setVisibility(mSns ? View.GONE : View.VISIBLE);
    }

    private void onClickSend() {

        View view = getView();
        if (view == null) return;

        String purpose = ((EditText)view.findViewById(R.id.purposeEditText)).getText().toString();
        String description = ((EditText)view.findViewById(R.id.descriptionEditText)).getText().toString();
        String notes = ((EditText)view.findViewById(R.id.notesEditText)).getText().toString();
        String email = ((EditText)view.findViewById(R.id.emailEditText)).getText().toString();

        if ((purpose.length() == 0)) {
            Dialog.show(getActivity(), Dialog.Style.error, "エラー", "アプリの目的が未入力です", null);
            return;
        }
        if ((description.length() == 0)) {
            Dialog.show(getActivity(), Dialog.Style.error, "エラー", "アプリの概要が未入力です", null);
            return;
        }
        if ((email.length() == 0)) {
            Dialog.show(getActivity(), Dialog.Style.error, "エラー", "ご連絡先が未入力です", null);
            return;
        }
        if (mIsAgree == false) {
            Dialog.show(getActivity(), Dialog.Style.error, "エラー", "利用規約への同意が必要です", null);
            return;
        }

        EstimateRequester.EstimateRequestData request = new EstimateRequester.EstimateRequestData();

        String targetId = "";
        if (mEngineerDetailData != null) {
            targetId = mEngineerDetailData.id;
        }
        request.target = targetId;
        request.purpose = purpose;
        request.description = description;
        request.ios = mIos;
        request.android = mAndroid;
        request.chat = mChat;
        request.camera = mCamera;
        request.movie = mMovie;
        request.push = mPush;
        request.map = mMap;
        request.geofence = mGeofence;
        request.settle = mSettlement;
        request.credit = mCreditCard;
        request.user = mUser;
        request.sns = mSns;
        request.notes = notes;
        request.email = email;

        Loading.start(getActivity());

        EstimateRequester.send(request, new EstimateRequester.EstimateRequesterCallback() {
            @Override
            public void didReceiveData(boolean result) {

                Loading.stop(getActivity());

                if (result) {
                    Dialog.show(getActivity(), Dialog.Style.success, "送信が完了しました", "返信があるまでお待ちください", new Dialog.DialogCallback() {
                        @Override
                        public void didClose() {
                            popFragment(AnimationType.horizontal);
                        }
                    });
                } else {
                    Dialog.show(getActivity(), Dialog.Style.error, "エラー", "通信に失敗しました", null);
                }
            }
        });
    }
}
