package com.bookshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bookshop.R;
import com.bookshop.bean.BookBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class BookGridView extends GridView{
    GridAdapter adapter;
    List<BookBean> data = new ArrayList<>();
    public BookGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumColumns(2);
        setVerticalScrollBarEnabled(false);
        adapter = new GridAdapter(context,data);
        setAdapter(adapter);

    }

    public void setData(List<BookBean> data){
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    class GridAdapter extends BaseAdapter{
        Context context;
        List<BookBean> data;
        GridAdapter(Context context, List<BookBean> data){
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public BookBean getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_order,null,true);
                holder = new ViewHolder();
                holder.iv_icon = (SquareImageView)convertView.findViewById(R.id.item_order_icon);
                holder.tv_name = (TextView) convertView.findViewById(R.id.item_order_name);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            BookBean bean = getItem(position);
            holder.tv_name.setText(bean.getBookName());
            Glide.with(context).
                    load("file:///android_asset/img/"+bean.getBook_image())
                    .centerCrop()
                    .into(holder.iv_icon);
            return convertView;
        }
    }

    class ViewHolder{
        SquareImageView iv_icon;
        TextView tv_name;
    }
}
