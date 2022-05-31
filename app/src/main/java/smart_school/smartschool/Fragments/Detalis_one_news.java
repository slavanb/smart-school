package smart_school.smartschool.Fragments;

import android.os.Bundle;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import smart_school.smartschool.Activity.Container;
import smart_school.smartschool.Model.List_news;
import smart_school.smartschool.R;

public class Detalis_one_news extends Fragment {

    private View view;
    @BindView(R.id.tv_detalis)
    TextView tvDetalis;
    @BindView(R.id.tv_header_news)TextView tvHeaderNews;
    @BindView(R.id.tv_date_news)TextView tvDateNews;
    @BindView(R.id.img_news)ImageView imgNews;
    @BindView(R.id.img_arrow_back)ImageView imgArrowBack;
    @BindView(R.id.fr_back)FrameLayout frBack;
    private String id;
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.detalis_one_news, container, false);
        }
        ButterKnife.bind(this, view);
        mRealm=Realm.getDefaultInstance();

        try {
            id = getArguments().getString("id");
            } catch (Exception e) {
            Log.i("TAG", String.valueOf(e));
        }
       List_news detalis=mRealm.where(List_news.class).equalTo("iD",Integer.parseInt(id)).findFirst();
        if (detalis!=null){
            tvDetalis.setText(Html.fromHtml(detalis.getPOSTCONTENT()));
            tvDetalis.setMovementMethod(LinkMovementMethod.getInstance());
           // tvDetalis.setText(detalis.getPOSTCONTENT());
            tvHeaderNews.setText(detalis.getPOSTTITLE());
            tvDateNews.setText(detalis.getPOSTDATE());
            Glide.with(getActivity()).load(detalis.getIMAGE()).into(imgNews);


        }

        frBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Container)getActivity()).GetNotReadMess();



        return view;
    }
}
