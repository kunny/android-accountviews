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

package com.androidhuman.accountviews.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidhuman.accountviews.AccountView;
import com.androidhuman.accountviews.sample.R;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private String[] menus = new String[]{
            "AccountView - from Java code",
            "AccountView - from XML"
    };

    ListView lvMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMenus = (ListView) findViewById(android.R.id.list);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menus);
        lvMenus.setAdapter(adapter);
        lvMenus.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                startActivity(new Intent(this, AccountViewJavaActivity.class));
                break;
            case 1:

                break;
        }
    }
}
