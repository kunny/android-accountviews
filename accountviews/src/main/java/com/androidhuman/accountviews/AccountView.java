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

    ImageView ivProfileImage;
    LinearLayout llTextContainer;
    TextView tvPrimary;
    TextView tvSecondary;

    public static final int STYLE_COMPOSITE = 0;
    public static final int STYLE_LINEAR = 1;

    private int layout = STYLE_COMPOSITE;
    private int profileImageSrc = -1;
    private String primaryText;
    private String secondaryText;

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
            profileImageSrc = a.getResourceId(R.styleable.AccountView_profileImageSrc, -1);

            primaryText = a.getString(R.styleable.AccountView_primaryText);
            secondaryText = a.getString(R.styleable.AccountView_secondaryText);
        }

        // Inflate selected layout into view
        LayoutInflater.from(context).inflate(getLayout(), this, true);

        ivProfileImage = (ImageView) findViewById(R.id.iv_profile);
        tvPrimary = (TextView) findViewById(R.id.tv_primary);
        tvSecondary = (TextView) findViewById(R.id.tv_secondary);

        // Apply values
        if (profileImageSrc != -1) {
            ivProfileImage.setImageResource(profileImageSrc);
        }else{
            ivProfileImage.setVisibility(View.GONE);
        }

        if(primaryText!=null){
            tvPrimary.setText(primaryText);
        }

        if(secondaryText!=null){
            tvSecondary.setText(secondaryText);
        }

        if(isInEditMode()){
            ivProfileImage.setImageResource(R.drawable.ic_launcher);
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

    public void setProfileImage(int resId){
        ivProfileImage.setImageResource(resId);
        ivProfileImage.setVisibility(View.VISIBLE);
    }

    private int getLayout(){
        switch(layout){
            case STYLE_COMPOSITE:
                return com.androidhuman.accountviews.R.layout.rl_composite;
            case STYLE_LINEAR:
                return com.androidhuman.accountviews.R.layout.rl_linear;
            default:
                throw new IllegalArgumentException();
        }
    }
}
