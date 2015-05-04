package cn.yunzhisheng.prodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.status_bar_main);
		((ListView) findViewById(R.id.lv_functions)).setAdapter(new FunctionsAdapter());
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
			case 0:// 在线语言识别示例
				intent = new Intent(this, ASROnlineActivity.class);
				break;
			case 1:// 离线语音识别示例
				intent = new Intent(this, ASROfflineActivity.class);
				break;
			case 2:// 离线语音合成示例
				intent = new Intent(this, TTSOfflineActivity.class);
				break;
			case 3:// 语音唤醒示例
				intent = new Intent(this, WakeupOfflineActivity.class);
				break;
			default:
				break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	private String[] functions = { "在线识别", "本地识别", "本地合成", "本地唤醒" };

	private class FunctionsAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return functions.length;
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.button_list_item, null);
				holder = new ViewHolder();
				holder.btn = (Button) convertView.findViewById(R.id.btn);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.btn.setText(functions[position]);
			holder.btn.setId(position);
			holder.btn.setOnClickListener(MainActivity.this);
			return convertView;
		}
	}

	public final class ViewHolder {
		public Button btn;
	}

}
