# Android-Common-Adapter
This is a common adapter for ListView 


## USEAGE:

extends an adapter from `CommonBaseAdapter`

```java
public class MessageAdapter extends CommonBaseAdapter<Message> {
	
	public MessageAdapter(Context context, List<Message> mDatas, int[] itemLayoutIds, DisPlayImageType imageType) {
		super(context, mDatas, itemLayoutIds, imageType);
	}

	@Override
	public void convertItemView(final CommonViewHolder holder, final Message item, final int position) {
		
		holder.setText(R.id.item_message_title, item.title);
		holder.setText(R.id.item_message_time, TimeUtil.getStandardDate(item.askReplyTime));
		holder.setText(R.id.item_message_detail, item.content);
		
		holder.setImageResource(R.id.item_message_icon, R.drawable.message_icon_alert);
		
		final ImageView editBtn = holder.getView(R.id.item_message_edit_icon);
		
		if(item.isSelected)
			editBtn.setBackgroundResource(R.drawable.download_check_btn);
		else
			editBtn.setBackgroundResource(R.drawable.download_uncheck_btn);
		
		if(isEditorState){
			editBtn.setVisibility(View.VISIBLE);
		}else{
			editBtn.setVisibility(View.GONE);
		}
		
		holder.setOnClickListenr(R.id.message_list_item_root, new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!isEditorState){
					listener.onItemClick( holder.getPosititon() );
				}
			}
		});
		
		holder.setOnClickListenr(R.id.item_message_edit_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean state = !mDatas.get(holder.getPosititon()).isSelected;
				
				mDatas.get(holder.getPosititon()).isSelected = state;
				
				if(state)
					editBtn.setBackgroundResource(R.drawable.download_check_btn);
				else
					editBtn.setBackgroundResource(R.drawable.download_uncheck_btn);
				
				listener.onSelectClick( holder.getPosititon() );
			}
		});
	}
	
	public void setEditState(boolean isEditState){
		isEditorState = isEditState;
		notifyDataSetChanged();
	}
	
	public void setListener(MessageItemListener l){
		listener = l;
	}
}
```
