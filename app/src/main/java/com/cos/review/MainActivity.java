package com.cos.review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cos.review.adapter.ProductAdapter;
import com.cos.review.adapter.SearchKeywordAdapter;
import com.cos.review.model.Product;
import com.cos.review.model.SearchKeyword;
import com.cos.review.viewmodel.ProductViewModel;
import com.cos.review.viewmodel.SearchKeywordViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private RecyclerView rvContainer, rvMenu;
    private MainActivity mContext = MainActivity.this;
    private ProductAdapter productAdapter;
    private SearchKeywordAdapter searchKeywordAdapter;
    private SearchKeywordViewModel searchKeywordViewModel;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initSetting();
        initData();
//        sampleData();
    }


    private void initObject(){
        rvMenu = findViewById(R.id.rv_menu);
        rvContainer = findViewById(R.id.rv_container);

        searchKeywordAdapter = new SearchKeywordAdapter();
        productAdapter = new ProductAdapter();

        searchKeywordViewModel = ViewModelProviders.of(mContext).get(SearchKeywordViewModel.class);
        productViewModel = ViewModelProviders.of(mContext).get(ProductViewModel.class);
    }

    private void initSetting() {
        rvMenu.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        rvContainer.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));

        rvMenu.setAdapter(searchKeywordAdapter);
        rvContainer.setAdapter(productAdapter);
    }

    private void initData(){
        searchKeywordViewModel.데이터등록();
        searchKeywordViewModel.구독().observe(mContext, new Observer<List<SearchKeyword>>() {
            @Override
            public void onChanged(List<SearchKeyword> searchKeywords) {
                searchKeywordAdapter.setSearchKeywords(searchKeywords);
                searchKeywordAdapter.notifyDataSetChanged();
            }
        });

        productViewModel.데이터등록();
        productViewModel.구독().observe(mContext, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productAdapter.setProducts(products);
                productAdapter.notifyDataSetChanged();
            }
        });
    }

    // test
    private void sampleData(){
        List<SearchKeyword> searchKeywords = new ArrayList<>();
        searchKeywords.add(new SearchKeyword(1,"갤럭시20"));
        searchKeywords.add(new SearchKeyword(2,"아이폰12"));
        searchKeywords.add(new SearchKeyword(3,"맥북프로"));
        searchKeywords.add(new SearchKeyword(4,"아이폰11"));
        searchKeywordAdapter.setSearchKeywords(searchKeywords);

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().title("제목1").day("1일전").thumnail("https://t1.daumcdn.net/brunch/service/user/3rWZ/image/fiYqC-m8arDti2dUPSrfAZ8wBSw.jpg").build());
        products.add(Product.builder().title("제목2").day("2일전").thumnail("https://t1.daumcdn.net/brunch/service/user/3rWZ/image/fiYqC-m8arDti2dUPSrfAZ8wBSw.jpg").build());
        products.add(Product.builder().title("제목3").day("3일전").thumnail("https://t1.daumcdn.net/brunch/service/user/3rWZ/image/fiYqC-m8arDti2dUPSrfAZ8wBSw.jpg").build());
        productAdapter.setProducts(products);
    }

}