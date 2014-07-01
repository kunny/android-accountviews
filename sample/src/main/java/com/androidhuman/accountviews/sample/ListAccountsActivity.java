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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidhuman.accountviews.sample.R;

public class ListAccountsActivity extends ActionBarActivity {

    ListView lvAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_accounts);

        lvAccounts = (ListView) findViewById(android.R.id.list);
        lvAccounts.setAdapter(new AccountAdapter(this));

    }

    class AccountAdapter extends BaseAdapter {
        private Context context;
        private Account[] accounts;

        public AccountAdapter(Context context){
            super();
            this.context = context;
            accounts = AccountManager.get(context).getAccounts();
        }

        @Override
        public int getCount() {
            return accounts.length;
        }

        @Override
        public Account getItem(int i) {
            return accounts[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            Account item = getItem(i);

            StringBuilder builder = new StringBuilder();
            builder.append("Name : ").append(item.name).append('\n')
                    .append("Type : ").append(item.type);
            tv.setText(builder.toString());
            return view;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
