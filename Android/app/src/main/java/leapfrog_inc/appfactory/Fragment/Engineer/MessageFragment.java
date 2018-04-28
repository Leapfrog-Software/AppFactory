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
import leapfrog_inc.appfactory.Function.SaveData;
import leapfrog_inc.appfactory.Http.Requester.EngineerDetailRequester;
import leapfrog_inc.appfactory.Http.Requester.MessageRequester;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/22.
 */

public class MessageFragment extends BaseFragment {

    private EngineerDetailRequester.EngineerDetailResponseData mEngineerDetailData;
    private boolean mIsAgree = false;

    public void set(EngineerDetailRequester.EngineerDetailResponseData engineerDetailData) {
        mEngineerDetailData = engineerDetailData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_message, null);

        initContents(view);
        initAction(view);

        return view;
    }

    private void initContents(View view) {

        String imageUrl = Constants.ServerEngineerImageDirectory + mEngineerDetailData.id;
        PicassoUtility.getEngineerImage(getActivity(), imageUrl, (ImageView)view.findViewById(R.id.engineerImageView));

        ((TextView)view.findViewById(R.id.nameTextView)).setText(mEngineerDetailData.name);

    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment(AnimationType.horizontal);
            }
        });

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

    private void onClickSend() {

        View view = getView();
        if (view == null) return;

        String message = ((EditText)view.findViewById(R.id.messageEditText)).getText().toString();
        String email = ((EditText)view.findViewById(R.id.emailEditText)).getText().toString();

        if ((message.length() == 0)) {
            Dialog.show(getActivity(), Dialog.Style.error, "エラー", "メッセージが未入力です", null);
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

        Loading.start(getActivity());

        MessageRequester.send(email, mEngineerDetailData.id, message, new MessageRequester.MessageRequesterCallback() {
            @Override
            public void didReceiveData(boolean result) {

                Loading.stop(getActivity());

                if (result) {
                    Dialog.show(getActivity(), Dialog.Style.success, "完了", "メッセージを送信しました", new Dialog.DialogCallback() {
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
