# Android-Common-Adapter
This is a common adapter for ListView 


## USEAGE:

extends an adapter from `CommonBaseAdapter`

```java
public class MessageAdapter extends CommonBaseAdapter<Message> {
	
	public MessageAdapter(Context context, List<Message> mDatas) {
		super(context, mDatas, new int[]{R.layout.item_message});
	}

	@Override
	public void convertItemView(final CommonViewHolder holder, final Message item, final int position) {
	
		// 现在封装了一些常用的view的设置,可不断根据需要完善
		// CommonViewHolder的设置
		holder.setText(R.id.item_message_title, item.title);
		holder.setText(R.id.item_message_time, item.time);
		holder.setText(R.id.item_message_detail, item.content);
		
		// 或者直接采取以下方式获取view设置内容
		CheckBox mCheckBox = holder.getView(R.id.item_msg_checkbox);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// do something here
				
			}
		});
	}
}
```
