/*
 * Copyright(c) 2014 Taeho Kim <jyte82@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidhuman.accountviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by kunny on 2014. 6. 23..
 */
public class AccountView extends RelativeLayout {

    View vProfileBackgroundShadow;
    ImageView ivProfileBackground;
    ImageView ivProfileBadge;

    TextView tvPrimary;
    TextView tvSecondary;

    public static final int STYLE_COMPOSITE = 0;
    public static final int STYLE_LINEAR = 1;
    public static final int STYLE_LINEAR_EXPANDED = 2;

    // View attributes
    private int layout = STYLE_COMPOSITE;
    private int backgroundImageSrc = -1;
    private int backgroundColor = -1;
    private int badgeImageSrc = -1;
    private String primaryText;
    private String secondaryText;

    private boolean showPrimaryText = true;
    private boolean showSecondaryText = true;
    private boolean showProfileBadge = true;
    private boolean showProfileBackground = true;

    private boolean circleFrame = true;

    public AccountView(Context context) {
        super(context);
        initialize(context, null);
    }

    public AccountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public AccountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs){
        // Get attributes from XML declaration
        if(attrs!=null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AccountView);

            layout = a.getInt(R.styleable.AccountView_layout, STYLE_COMPOSITE);
            backgroundImageSrc = a.getResourceId(R.styleable.AccountView_profileBackgroundSrc, -1);
            backgroundColor = a.getColor(R.styleable.AccountView_profileBackgroundColor, -1);
            badgeImageSrc = a.getResourceId(R.styleable.AccountView_profileBadgeSrc, -1);

            primaryText = a.getString(R.styleable.AccountView_primaryText);
            secondaryText = a.getString(R.styleable.AccountView_secondaryText);

            showPrimaryText = a.getBoolean(R.styleable.AccountView_showPrimaryText, true);
            showSecondaryText = a.getBoolean(R.styleable.AccountView_showSecondaryText, true);
            showProfileBadge = a.getBoolean(R.styleable.AccountView_showProfileBadge, true);
            showProfileBackground = a.getBoolean(R.styleable.AccountView_showProfileBackground, true);

            circleFrame = a.getBoolean(R.styleable.AccountView_circleFrame, true);
            a.recycle();
        }

        // Inflate selected layout into view
        LayoutInflater.from(context).inflate(getLayout(), this, true);

        vProfileBackgroundShadow = findViewById(R.id.v_profile_background_shadow);
        ivProfileBackground = (ImageView) findViewById(R.id.iv_profile_background);
        ivProfileBadge = (ImageView) findViewById(R.id.iv_profile_badge);
        tvPrimary = (TextView) findViewById(R.id.tv_primary);
        tvSecondary = (TextView) findViewById(R.id.tv_secondary);

        // Apply values
        if (backgroundImageSrc != -1){
            setProfileBackground(backgroundImageSrc);
            showProfileBackground(true);
        }

        if (backgroundColor != -1){
            setBackgroundColor(backgroundColor);
            showProfileBackground(true);
        }

        if (badgeImageSrc != -1) {
            setProfileBadge(badgeImageSrc);
        }else{
            ivProfileBadge.setVisibility(View.GONE);
        }

        if(primaryText!=null){
            tvPrimary.setText(primaryText);
        }

        if(secondaryText!=null){
            tvSecondary.setText(secondaryText);
        }

        showPrimaryText(showPrimaryText);
        showSecondaryText(showSecondaryText);
        showProfileBadge(showProfileBadge);
        showProfileBackground(showProfileBackground);

        if(isInEditMode()){
            ivProfileBadge.setImageResource(com.androidhuman.accountviews.R.drawable.ic_launcher);
            ivProfileBackground.setImageResource(com.androidhuman.accountviews.R.drawable.bg_profile);
            tvPrimary.setText("Taeho Kim");
            tvSecondary.setText("jyte82@gmail.com");
        }
    }

    public void setPrimaryText(String text){
        tvPrimary.setText(text);
    }

    public void setSecondaryText(String text){
        tvSecondary.setText(text);
    }

    public void setProfileBadge(int resId){
        //if(circleFrame) {
        //    Drawable drawable = new RoundedDrawable(getResources(), badgeImageSrc);
        //    ivProfileBadge.setImageDrawable(drawable);
        //}else{
            ivProfileBadge.setImageResource(resId);
        //}
    }

    public void setProfileBadge(Bitmap bitmap){
        //if(circleFrame) {
        //    Drawable drawable = new RoundedDrawable(bitmap);
        //    ivProfileBadge.setImageDrawable(drawable);
        //}else {
            ivProfileBadge.setImageBitmap(bitmap);
        //}
    }

    public void setProfileBadge(Drawable drawable) {
        ivProfileBadge.setImageDrawable(drawable);
    }

    public void setProfileBackground(int resId){
        ivProfileBackground.setImageResource(resId);
    }

    public void setProfileBackground(Bitmap bitmap){
        ivProfileBackground.setImageBitmap(bitmap);
    }

    public void setProfileBackground(Drawable drawable) {
        ivProfileBackground.setImageDrawable(drawable);
    }

    public void setProfileBackgroundColor(int color){
        ivProfileBackground.setBackgroundColor(color);
    }

    public void showProfileBadge(boolean show){
        ivProfileBadge.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showProfileBackground(boolean show){
        ivProfileBackground.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public void showPrimaryText(boolean show){
        tvPrimary.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showSecondaryText(boolean show){
        tvSecondary.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private int getLayout(){
        switch(layout){
            case STYLE_COMPOSITE:
                return com.androidhuman.accountviews.R.layout.rl_composite;
            case STYLE_LINEAR:
                return com.androidhuman.accountviews.R.layout.rl_linear;
            case STYLE_LINEAR_EXPANDED:
                return com.androidhuman.accountviews.R.layout.rl_linear_expanded;
            default:
                throw new IllegalArgumentException();
        }
    }
}
