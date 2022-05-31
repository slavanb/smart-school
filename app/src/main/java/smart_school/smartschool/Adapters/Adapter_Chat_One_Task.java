package smart_school.smartschool.Adapters;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import smart_school.smartschool.Model.One_dialog_messages.Message;
import smart_school.smartschool.R;

/**
 * Created by Dell on 24.11.2017.
 */

public class Adapter_Chat_One_Task extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Message> listInfo=new ArrayList<>();
    private String name;
    private Context mContext;
    private String avatar;
    private String MyAvatar;
    private int my;


    public Adapter_Chat_One_Task(Context mContext, List<Message> listInfo, String name, String avatar, String MyAvatar) {
        this.listInfo = listInfo;
        this.mContext = mContext;
        this.name=name;
        this.avatar=avatar;
        this.MyAvatar=MyAvatar;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return listInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return listInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewNew;
        final Message item = listInfo.get(i);
        if (item.getIs_my_message().equalsIgnoreCase("1")) {

         //   if (viewNew==null){          }
            viewNew=mInflater.inflate(R.layout.item_one_dialog_message_my,viewGroup,false);

          //  TextView tvNameUser = (TextView) viewNew.findViewById(R.id.tv_avtor);
            TextView date = (TextView) viewNew.findViewById(R.id.tv_date);
            TextView tvText = (TextView) viewNew.findViewById(R.id.tv_text_message);

            String[] dyrtiDate=item.getDateTime().split(" ");
            String[]dyrtiYear=dyrtiDate[0].split("-");
            String[]dyrtiTime=dyrtiDate[1].split(":");

            date.setText(dyrtiYear[2]+"."+dyrtiYear[1]+" "+dyrtiTime[0]+":"+dyrtiTime[1]);
           // tvText.setText(item.getText());

            tvText.setText(Html.fromHtml(item.getText()));
            tvText.setMovementMethod(LinkMovementMethod.getInstance());


        } else {

          //  if (viewNew==null){           }
            viewNew=mInflater.inflate(R.layout.item_one_dialog_message,viewGroup,false);

            CircleImageView imgAva=(CircleImageView)viewNew.findViewById(R.id.img_ava);

            Glide.with(mContext).load(avatar).into(imgAva);

           // TextView tvNameUser = (TextView) viewNew.findViewById(R.id.tv_avtor);
            TextView date = (TextView) viewNew.findViewById(R.id.tv_date);
            TextView tvText = (TextView) viewNew.findViewById(R.id.tv_text_message);


//            tvNameUser.setText(name);

            String[] dyrtiDate=item.getDateTime().split(" ");
            String[]dyrtiYear=dyrtiDate[0].split("-");
            String[]dyrtiTime=dyrtiDate[1].split(":");

            date.setText(dyrtiYear[2]+"."+dyrtiYear[1]+" "+dyrtiTime[0]+":"+dyrtiTime[1]);
          //  tvText.setText(item.getText());
            tvText.setText(Html.fromHtml(item.getText()));
            tvText.setMovementMethod(LinkMovementMethod.getInstance());


        }


        return viewNew;
    }
}
