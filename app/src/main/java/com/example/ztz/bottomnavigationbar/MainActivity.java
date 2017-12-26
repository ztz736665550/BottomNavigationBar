package com.example.ztz.bottomnavigationbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.ztz.bottomnavigationbar.fragment.ClassificationFragment;
import com.example.ztz.bottomnavigationbar.fragment.FindFragment;
import com.example.ztz.bottomnavigationbar.fragment.HomeFragment;
import com.example.ztz.bottomnavigationbar.fragment.MineFragment;
import com.example.ztz.bottomnavigationbar.fragment.ShopCartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * BottomNavigationBar最新版本没有BadgeItem方法
 *
 * BottomNavigationBar实现底部导航
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private FragmentTransaction transaction;
    private ClassificationFragment classificationFragment;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private ShopCartFragment shopCartFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //按钮右上角红点
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.red)
                .setBorderWidth(0);

        bottomNavigationBar.setActiveColor(R.color.colorAccent)//设置Item选中颜色方法
                .setInActiveColor(R.color.colorPrimary)//设置Item未选中颜色方法
                .setBarBackgroundColor("#FFFFFF");//背景颜色
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//按钮的样式
        //背景样式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.shouye,"首页").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.fenlei,"分类"))
                .addItem(new BottomNavigationItem(R.drawable.faxian,"发现"))
                .addItem(new BottomNavigationItem(R.drawable.gouwuche,"购物车"))
                .addItem(new BottomNavigationItem(R.drawable.mine,"我的"))
                .setFirstSelectedPosition(0)//默认选中页面
                .initialise();
        //bottomNavigationBar的选中事件
        bottomNavigationBar.setTabSelectedListener(this);
        //默认选中的fragment
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        findFragment = new FindFragment();
        shopCartFragment = new ShopCartFragment();
        mineFragment = new MineFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, homeFragment)
                   .add(R.id.frameLayout,classificationFragment)
                   .add(R.id.frameLayout, findFragment)
                   .add(R.id.frameLayout, shopCartFragment)
                   .add(R.id.frameLayout, mineFragment)
                   .show(homeFragment)
                   .hide(classificationFragment)
                   .hide(findFragment)
                   .hide(shopCartFragment)
                   .hide(mineFragment)
                   .commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            case 0:
                fragmentTransaction.show(homeFragment)
                        .hide(classificationFragment)
                        .hide(findFragment)
                        .hide(shopCartFragment)
                        .hide(mineFragment)
                        .commit();

                break;
            case 1:
                fragmentTransaction.hide(homeFragment)
                        .show(classificationFragment)
                        .hide(findFragment)
                        .hide(shopCartFragment)
                        .hide(mineFragment)
                        .commit();
                break;
            case 2:
                fragmentTransaction.hide(homeFragment)
                        .hide(classificationFragment)
                        .show(findFragment)
                        .hide(shopCartFragment)
                        .hide(mineFragment)
                        .commit();
                break;
            case 3:
                fragmentTransaction.hide(homeFragment)
                        .hide(classificationFragment)
                        .hide(findFragment)
                        .show(shopCartFragment)
                        .hide(mineFragment)
                        .commit();
                break;
            case 4:
                fragmentTransaction.hide(homeFragment)
                        .hide(classificationFragment)
                        .hide(findFragment)
                        .hide(shopCartFragment)
                        .show(mineFragment)
                        .commit();
                break;
            default:
                fragmentTransaction.show(homeFragment)
                        .hide(classificationFragment)
                        .hide(findFragment)
                        .hide(shopCartFragment)
                        .hide(mineFragment)
                        .commit();
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
