package com.example.s1541.aidrone;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import org.jetbrains.annotations.NotNull;


public class TabFragment3 extends Fragment {

    int selectedCount;

    Shimmer shimmer;
    ShimmerTextView shimmerTextView;

   BubblePicker picker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.tab_fragment3, container, false);

        shimmerTextView = (ShimmerTextView) RootView.findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();
        shimmer.start(shimmerTextView);

        picker = (BubblePicker) RootView.findViewById(R.id.picker);

        selectedCount = 0;

        final String[] titles = getResources().getStringArray(R.array.explain);
        final TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        final TypedArray images = getResources().obtainTypedArray(R.array.images);

        picker.setBubbleSize(3);
        picker.setCenterImmediately(true);
        picker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return titles.length;
            }

            @NotNull
            @Override
            public PickerItem getItem(int position) {
                PickerItem item = new PickerItem();
                item.setTitle(titles[position]);
                item.setGradient(new BubbleGradient(colors.getColor((position * 2) % 8, 0),
                        colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL));
                //item.setTypeface(mediumTypeface);
                item.setTextSize(80);
                item.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
                item.setBackgroundImage(ContextCompat.getDrawable(getActivity(), images.getResourceId(position, 0)));
                return item;
            }
        });

        picker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(PickerItem pickerItem) {
                selectedCount++;
            }

            @Override
            public void onBubbleDeselected(PickerItem pickerItem) {
                selectedCount--;
            }
        });

        return RootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        picker.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        picker.onPause();
    }
}