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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by kunny on 7/2/14.
 */
public class RoundedDrawable extends Drawable {

    private Bitmap bitmap;

    private RectF rect = new RectF();
    private Paint paint = new Paint();

    private int width;
    private int height;

    public RoundedDrawable(Bitmap bitmap){
        this.bitmap = bitmap;
        init();
    }

    public RoundedDrawable(Resources res, int resId){
        this.bitmap = BitmapFactory.decodeResource(res, resId);
        init();
    }

    private void init(){
        if(this.bitmap==null){
            throw new IllegalStateException("Bitmap is null");
        }
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setShader(new BitmapShader(bitmap,
                            Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    }

    @Override
    public void setAlpha(int i) {
        if(paint.getAlpha()!=i){
            paint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        rect.set(bounds);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return this.width;
    }

    @Override
    public int getIntrinsicHeight() {
        return this.height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(rect, paint);
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        paint.setFilterBitmap(filter);
        invalidateSelf();
    }

    @Override
    public void setDither(boolean dither) {
        paint.setDither(dither);
        invalidateSelf();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(bitmap!=null){
            bitmap.recycle();
            bitmap = null;
        }
    }
}
