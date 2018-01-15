package com.example.ztz.bottomnavigationbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * BottomNavigationBar最新版本(2.0.4)没有BadgeItem方法,不知道为什么
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
    private Fragment mFragment;//当前显示的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //底部导航栏
        BottomNavigationBar();
        //bottomNavigationBar的选中事件
        bottomNavigationBar.setTabSelectedListener(this);
        //fragment联动
        initFragment();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        findFragment = new FindFragment();
        shopCartFragment = new ShopCartFragment();
        mineFragment = new MineFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, homeFragment)
                   .commit();
        mFragment = homeFragment;
    }

    private void BottomNavigationBar() {
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
    }

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                switchFragment(homeFragment);
                break;
            case 1:
                switchFragment(classificationFragment);
                break;
            case 2:
                switchFragment(findFragment);
                break;
            case 3:
                switchFragment(shopCartFragment);
                break;
            case 4:
                switchFragment(mineFragment);
                break;
        }
    }
    @Override
    public void onTabUnselected(int position) {}
    @Override
    public void onTabReselected(int position) {}

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.frameLayout,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
}
