package com.bookshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bookshop.R;
import com.bookshop.bean.OrderBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class OrderListView extends ListView{

    private List<OrderBean> data = new ArrayList<>();
    private ListAdapter adapter;

    public OrderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        adapter = new ListAdapter(context,data);
        setAdapter(adapter);
        setDividerHeight(0);
        setDivider(null);

    }

    public void setData(List<OrderBean> data){
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    class ListAdapter extends BaseAdapter{
        Context context;
        List<OrderBean> data;
        ListAdapter(Context context,List<OrderBean> data){
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public OrderBean getItem(int position) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_order_list,null,true);
                holder = new ViewHolder();
                holder.init(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setData(context,getItem(position));
            return convertView;
        }
    }
    class ViewHolder{
        TextView tv_total,tv_time,tv_bookname,tv_count;
        ImageView img_icon;

        void init(View convertView){
//            tv_price = (TextView)convertView.findViewById(R.id.item_order_price);
            tv_total = (TextView)convertView.findViewById(R.id.item_order_total);
            tv_time = (TextView)convertView.findViewById(R.id.item_order_time);
            tv_bookname = (TextView)convertView.findViewById(R.id.item_order_bookname);
            tv_count = (TextView)convertView.findViewById(R.id.item_order_buycount);
            img_icon = (ImageView)convertView.findViewById(R.id.item_order_icon);
        }

        void setData(Context context,OrderBean bean){
            tv_bookname.setText("书名："+bean.getBookName());
            tv_count.setText("购买数量："+bean.getBookNum());
            tv_total.setText("总价："+bean.getTotal_NUM());
            tv_time.setText("购买时间："+bean.getOrder_TIME());
            Glide.with(context).load("file:///android_asset/img/"+bean.getBook_image()).into(img_icon);
        }
    }
}
