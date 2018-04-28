package leapfrog_inc.appfactory.Fragment.Tutorial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/28.
 */

public class TutorialPagerFragment extends Fragment {

    private int mIndex = 0;

    public void setTutorialIndex(int index) {
        mIndex = index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_pager_tutorial, null);

        initContent(view);

        return view;
    }

    private void initContent(View view) {

        String[] titleList = {"アプリファクトリーへ\nようこそ",
                                "低価格",
                                "品質",
                                "スピーディ",
                                "フレキシブル",};
        ((TextView)view.findViewById(R.id.titleTextView)).setText(titleList[mIndex]);


        ImageView imageView = view.findViewById(R.id.imageView);
        if (mIndex == 0)        imageView.setImageResource(R.drawable.tutorial1);
        else if (mIndex == 1)   imageView.setImageResource(R.drawable.tutorial2);
        else if (mIndex == 2)   imageView.setImageResource(R.drawable.tutorial3);
        else if (mIndex == 3)   imageView.setImageResource(R.drawable.tutorial4);
        else                    imageView.setImageResource(R.drawable.tutorial5);

        String[] messageList = {"アプリファクトリーは、\nスマホアプリ開発に特化した\nクラウドソーシングサービスです。",
                "一般的な開発会社の開発費と比べ、\n低予算で発注が可能です。",
                "技術力が高く認められた開発者様に\n限定して公開しています。",
                "開発者様に直接依頼する事により\nスムーズにプロジェクトが進行します。",
                "納品後も手厚いサポートにより\n仕様変更や運用保守が行われます。",};
        ((TextView)view.findViewById(R.id.messageTextView)).setText(messageList[mIndex]);
    }
}
