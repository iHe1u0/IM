package com.imorning.im.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imorning.im.R;
import com.imorning.im.bean.ApplicationData;
import com.imorning.im.bean.MessageTabEntity;

import java.util.List;

public class FriendMessageAdapter extends BaseAdapter {
    private final List<MessageTabEntity> mMessageEntities;
    private final LayoutInflater mInflater;
    private final Context mContext0;

    public FriendMessageAdapter(Context context, List<MessageTabEntity> vector) {
        this.mMessageEntities = vector;
        mInflater = LayoutInflater.from(context);
        mContext0 = context;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup root) {
        ImageView avatarView;
        TextView nameView;
        TextView unReadCountView;
        TextView sendTimeView;
        TextView contentView;
        MessageTabEntity message = mMessageEntities.get(position);
        Integer senderId = message.getSenderId();
        String name = message.getName();
        Bitmap photo = (ApplicationData.getInstance().getFriendPhotoMap()).get(senderId);
        int messageType = message.getMessageType();
        String sendTime = message.getSendTime();
        int unReadCount = message.getUnReadCount();
        String content = message.getContent();
        convertView = mInflater.inflate(R.layout.fragment_message_item, null);
        avatarView = (ImageView) convertView.findViewById(R.id.user_photo);
        nameView = (TextView) convertView.findViewById(R.id.user_name);
        contentView = (TextView) convertView.findViewById(R.id.user_message);
        unReadCountView = (TextView) convertView.findViewById(R.id.unread_message_count);
        sendTimeView = (TextView) convertView.findViewById(R.id.send_time);
        if (unReadCount == 0) {
            unReadCountView.setVisibility(View.GONE);
        } else if (unReadCount > 9) {
            unReadCountView.setText("9+");
        } else {

            unReadCountView.setText(message.getUnReadCount() + "");
        }
        if (photo != null) {
            avatarView.setImageBitmap(photo);
        }
        nameView.setText(name);
        sendTimeView.setText(sendTime);
        // System.out.println(messageType + "message类型");

        contentView.setText(content);

        return convertView;
    }

    public int getCount() {
        return mMessageEntities.size();
    }

    public Object getItem(int position) {
        return mMessageEntities.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

}
