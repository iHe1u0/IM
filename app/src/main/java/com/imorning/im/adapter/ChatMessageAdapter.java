package com.imorning.im.adapter;

import java.util.List;

import com.imorning.im.R;
import com.imorning.im.bean.ApplicationData;
import com.imorning.im.bean.ChatEntity;
import com.imorning.im.bean.User;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*public class ChatMessageAdapter extends ArrayAdapter<ChatEntity> {
	private int resourseId;

	public ChatMessageAdapter(Context context, int textViewResourseId,
			List<ChatEntity> vector) {
		super(context, textViewResourseId, vector);
		resourseId = textViewResourseId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatEntity chatEntity = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourseId, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout) view
					.findViewById(R.id.chat_friend_left_layout);
			viewHolder.rightLayout = (LinearLayout) view
					.findViewById(R.id.chat_user_right_layout);
			viewHolder.timeView = (TextView) view
					.findViewById(R.id.message_time);
			viewHolder.leftPhotoView = (ImageView) view
					.findViewById(R.id.message_friend_userphoto);
			viewHolder.rightPhotoView = (ImageView) view
					.findViewById(R.id.message_user_userphoto);
			viewHolder.leftMessageView = (TextView) view
					.findViewById(R.id.friend_message);
			viewHolder.rightMessageView = (TextView) view
					.findViewById(R.id.user_message);

		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		User user = ApplicationData.getInstance().getUserInfo();
		viewHolder.timeView.setText(chatEntity.getSendTime());
		if (chatEntity.getMessageType() == ChatEntity.SEND) {
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightPhotoView.setImageBitmap(ApplicationData
					.getInstance().getUserPhoto());
			viewHolder.rightMessageView.setText(chatEntity.getContent());
		} else if (chatEntity.getMessageType() == ChatEntity.RECEIVE) {// 本身作为接收方
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);

			viewHolder.leftPhotoView.setImageBitmap(ApplicationData
					.getInstance().getFriendPhotoMap()
					.get(chatEntity.getSenderId()));
			viewHolder.leftMessageView.setText(chatEntity.getContent());

		}
		return view;
	}

	class ViewHolder {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMessageView;
		TextView rightMessageView;
		TextView timeView;
		ImageView leftPhotoView;
		ImageView rightPhotoView;
	}

}*/
public class ChatMessageAdapter extends BaseAdapter {
	private List<ChatEntity> chatEntities;
	private LayoutInflater mInflater;
	private Context mContext0;

	public ChatMessageAdapter(Context context, List<ChatEntity> vector) {
		this.chatEntities = vector;
		mInflater = LayoutInflater.from(context);
		mContext0 = context;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMessageView;
		TextView rightMessageView;
		TextView timeView;
		ImageView leftPhotoView;
		ImageView rightPhotoView;
		view = mInflater.inflate(R.layout.chat_message_item_, null);
		ChatEntity chatEntity = chatEntities.get(position);
		leftLayout = (LinearLayout) view
				.findViewById(R.id.chat_friend_left_layout);
		rightLayout = (LinearLayout) view
				.findViewById(R.id.chat_user_right_layout);
		timeView = (TextView) view.findViewById(R.id.message_time);
		leftPhotoView = (ImageView) view
				.findViewById(R.id.message_friend_userphoto);
		rightPhotoView = (ImageView) view
				.findViewById(R.id.message_user_userphoto);
		leftMessageView = (TextView) view.findViewById(R.id.friend_message);
		rightMessageView = (TextView) view.findViewById(R.id.user_message);

		User user = ApplicationData.getInstance().getUserInfo();
		timeView.setText(chatEntity.getSendTime());
		if (chatEntity.getMessageType() == ChatEntity.SEND) {
			rightLayout.setVisibility(View.VISIBLE);
			leftLayout.setVisibility(View.GONE);

			rightPhotoView.setImageBitmap(ApplicationData.getInstance()
					.getUserPhoto());
			rightMessageView.setText(chatEntity.getContent());
		} else if (chatEntity.getMessageType() == ChatEntity.RECEIVE) {// 本身作为接收方
			leftLayout.setVisibility(View.VISIBLE);
			rightLayout.setVisibility(View.GONE);
			Bitmap photo = ApplicationData.getInstance().getFriendPhotoMap()
					.get(chatEntity.getSenderId());
			if (photo != null)
				leftPhotoView.setImageBitmap(photo);
			leftMessageView.setText(chatEntity.getContent());

		}
		return view;
	}

	@Override
	public int getCount() {
		return chatEntities.size();
	}

	@Override
	public Object getItem(int position) {
		return chatEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
