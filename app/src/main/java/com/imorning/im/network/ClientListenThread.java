package com.imorning.im.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import com.imorning.im.activity.SearchFriendActivity;
import com.imorning.im.activity.register.StepAccount;
import com.imorning.im.activity.register.StepPhoto;
import com.imorning.im.bean.ApplicationData;
import com.imorning.im.bean.TranObject;

import android.content.Context;

public class ClientListenThread extends Thread {
	private Socket mSocket = null;
	private Context mContext = null;
	private ObjectInputStream mOis;

	private boolean isStart = true;

	public ClientListenThread(Context context, Socket socket) {
		this.mContext = context;
		this.mSocket = socket;
		try {
			mOis = new ObjectInputStream(mSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSocket(Socket socket) {
		this.mSocket = socket;
	}

	@Override
	public void run() {
		try {
			isStart = true;
			while (isStart) {
				TranObject mReceived = null;
				//System.out.println("开始接受服务器");
				mReceived = (TranObject) mOis.readObject();
				System.out.println("接受成功");
				System.out.println(mReceived.getTranType());
				switch (mReceived.getTranType()) {
				case REGISTER_ACCOUNT:
					StepAccount.setRegisterInfo(mReceived, true);
					 System.out.println("注册账号成功");
					break;
				case REGISTER:
					StepPhoto.setRegisterInfo(mReceived, true);
					break;
				case LOGIN:
					ApplicationData.getInstance().loginMessageArrived(mReceived);
					break;
				case SEARCH_FRIEND:
					System.out.println("收到朋友查找结果");
					SearchFriendActivity.messageArrived(mReceived);
					break;
				case FRIEND_REQUEST:
					ApplicationData.getInstance().friendRequestArrived(mReceived);
					break;
				case MESSAGE:
					ApplicationData.getInstance().messageArrived(mReceived);
					break;
				default:
					break;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		isStart = false;
	}
}
