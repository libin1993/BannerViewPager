package com.example.viewpager.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viewpager.holder.HolderCreator;
import com.example.viewpager.holder.ViewHolder;
import com.example.viewpager.utils.ImageLoaderUtil;
import com.example.viewpager.view.CircleViewPager;

import java.util.List;

/**
 * Created by zhpan on 2017/3/28.
 */

public class CirclePagerAdapter<T> extends PagerAdapter {
    private List<T> list;
    private CircleViewPager viewPager;
    private HolderCreator holderCreator;

    public CirclePagerAdapter(List<T> list, CircleViewPager viewPager, HolderCreator holderCreator) {
        this.list = list;
        this.viewPager = viewPager;
        this.holderCreator = holderCreator;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = getView(position, container);
        container.addView(view);
        return view;
    }


    //  根据图片URL创建对应的ImageView并添加到集合
    private View getView(int position, ViewGroup container) {
        final int realPosition = position % getRealCount();
        ViewHolder holder = holderCreator.createViewHolder();
        if (holder == null) {
            throw new RuntimeException("can not return a null holder");
        }
        View view = holder.createView(container.getContext());
        if (list != null && list.size() > 0) {
            holder.onBind(container.getContext(), realPosition, list.get(realPosition));
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.imageClick(realPosition - 1);
            }
        });
        return view;
    }

    /**
     * 获取真实的Count
     *
     * @return
     */
    private int getRealCount() {
        return list == null ? 0 : list.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);

    }
}