package com.bookshop.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.bookshop.R;
import com.bookshop.bean.BookBean;
import com.bookshop.bean.OrderBean;
import com.bookshop.service.ServiceFactory;
import com.bookshop.view.BookGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BookGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("欢迎购买书本");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridView = (BookGridView)findViewById(R.id.main_gridview_books);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookBean bean = (BookBean) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this,OrderAddActivity.class);
                i.putExtra("bookBean", bean);
                startActivity(i);
            }
        });
        loadBooks();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_gallery) {
            //我的购物车
        } else if (id == R.id.nav_slideshow) {
            //我的订单
            startActivity(new Intent(MainActivity.this,OrderListActivity.class));
        } else if (id == R.id.nav_manage) {
            //退出登录
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadBooks(){

        ServiceFactory.bookService().getBookList("Android","")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        String json = response.string();
                        try{
                            List<BookBean> books = new Gson().fromJson(json, new TypeToken<List<BookBean>>(){}.getType());
                            gridView.setData(books);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
//        new AsyncTask<Void, Void, Void>() {
//            List<BookBean> books = new ArrayList<>();
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                for (int i=0;i<20;i++){
//                    BookBean book = new BookBean();
//                    book.setBookId("_id_"+i);
//                    book.setBookName("_name_"+i);
//                    book.setBookPrice("19.9");
//                    books.add(book);
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                gridView.setData(books);
//            }
//        }.execute();
    }

}
