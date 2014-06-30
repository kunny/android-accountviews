package com.androidhuman.accountviews;

import android.content.Context;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidhuman.accountpickerview.R;

/**
 * Created by kunny on 2014. 6. 23..
 */
public class AccountView extends RelativeLayout {

    private static final int ID_PROFILE_IMAGE = 0x1000;
    private static final int ID_TEXT_SECTION = 0x0110;
    private static final int ID_PRIMARY_TEXT = 0x0100;
    private static final int ID_SECONDARY_TEXT = 0x0010;

    ImageView ivProfileImage;
    LinearLayout llTextContainer;
    TextView tvPrimary;
    TextView tvSecondary;

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

        ivProfileImage = new ImageView(context);
        tvPrimary = new TextView(context);
        tvSecondary = new TextView(context);

        // Profile image view
        RelativeLayout.LayoutParams param =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        param.addRule(RelativeLayout.ALIGN_BOTTOM, ID_TEXT_SECTION);
        param.addRule(RelativeLayout.ALIGN_TOP, ID_TEXT_SECTION);
        param.setMargins(0, 0,
                (int)TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 10.f, getResources().getDisplayMetrics()), 0);

        ivProfileImage.setId(ID_PROFILE_IMAGE);
        if(attrs!=null || isInEditMode()) {
            int profileImageSrc =
                    attrs.getAttributeResourceValue("com.androidhuman.accountviews.AccountView",
                            "profileImageSrc", -1);
            if (profileImageSrc != -1) {
                ivProfileImage.setImageResource(profileImageSrc);
            }
        }

        addView(ivProfileImage, param);

        // Text container
        llTextContainer = new LinearLayout(context);
        llTextContainer.setId(ID_TEXT_SECTION);
        llTextContainer.setOrientation(LinearLayout.VERTICAL);

        param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        if(getChildCount()==0){ // If profile image is not added
            param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }else {
            param.addRule(RelativeLayout.RIGHT_OF, ID_PROFILE_IMAGE);
        }

        tvPrimary.setId(ID_PRIMARY_TEXT);

        tvPrimary.setTextAppearance(context, android.R.style.TextAppearance_Medium);
        llTextContainer.addView(tvPrimary);

        tvSecondary.setId(ID_SECONDARY_TEXT);
        llTextContainer.addView(tvSecondary);

        addView(llTextContainer, param);

        if(isInEditMode()){
            ivProfileImage.setImageResource(R.drawable.ic_launcher);
            tvPrimary.setText("John Doe");
            tvSecondary.setText("test@sample.com");
        }
    }

    public void setPrimaryText(String text){
        tvPrimary.setText(text);
    }

    public void setSecondaryText(String text){
        tvSecondary.setText(text);
    }

    public void setImage(int resId){
        ivProfileImage.setImageResource(resId);
    }
}
