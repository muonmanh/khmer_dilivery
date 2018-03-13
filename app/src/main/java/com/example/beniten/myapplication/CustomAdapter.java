package com.example.beniten.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Beniten on 3/13/2018.
 */

public class CustomAdapter extends ArrayAdapter<ContentValues> implements SectionIndexer {
    private LayoutInflater layoutInflater_;
    private int separetorResourceId = R.layout.list_header; // 区切り用のフィールド
    private final String SEP_FLAG = "sepFlag";
    private HashMap<String, Integer> indexer = new HashMap<String, Integer>();
    private String[] sections;

    // Constructor
    public CustomAdapter(Context context, int textViewResourceId, List<ContentValues> objects) {
        // 一度空のobjectで初期化 (addで区切りを入れながら追加するため)
        super(context, textViewResourceId, new ArrayList<ContentValues>());
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /// 区切りを挿入しながらデータを追加
        int listLength = objects.size();
        String pre_initial = ""; int sep_num = 0;
        for(int index=0; index<listLength; index++){
            ContentValues cv = objects.get(index);

            String initial = cv.getAsString("name").substring(0, 1); // nameの頭文字を基準に区切る
            if(!initial.equalsIgnoreCase(pre_initial)){ // 頭文字の判定(頭文字が変わったら追加)
                ContentValues cv_sep = new ContentValues();
                cv_sep.put(SEP_FLAG, true); cv_sep.put("text", initial);
                this.indexer.put(initial, index + sep_num);
                add(cv_sep); sep_num++;
                pre_initial = initial;
            }
            add(cv); // ArrayAdapterにObjectを追加
        }

        ArrayList<String> sectionList = new ArrayList<String>(indexer.keySet());
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        sectionList.toArray(sections);
    }

    // ViewHolderクラス
    static class ViewHolder{
        TextView textView_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // 特定の行(position)のデータを得る
        final ContentValues cv = (ContentValues)getItem(position);
        ViewHolder holder;

      /*  // convertViewは使い回しされている可能性があるのでnullの時と, 区切り用のビューに設定されている場合に新しく作成
        if (null == convertView || convertView.getId() != R.layout.listview) {
            convertView = layoutInflater_.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.textView_name = (TextView)convertView.findViewById(R.id.listview_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        if(!isEnabled(position)){ // 区切りの場合は, 区切り用のビューを変更する
            convertView =  layoutInflater_.inflate(separetorResourceId, null);
            TextView sep_text = (TextView)convertView.findViewById(R.id.listView_initial);
            sep_text.setText(cv.getAsString("text")); // 区切りに表示するテキストを設定
        }else{ // 区切りでない場合
            holder.textView_name.setText(cv.getAsString("name"));
        }*/

        return convertView;
    }

    @Override
    public boolean isEnabled(int position){ // 区切りの場合はfalse, そうでない場合はtrueを返す
        ContentValues cv = getItem(position);
        if(cv.containsKey(SEP_FLAG)){ // 区切りフラグの有無チェック
            return !cv.getAsBoolean(SEP_FLAG);
        }else{
            return true;
        }
    }

    // 以下は, SectionIndexerの実装部分

    @Override
    public int getPositionForSection(int section) {
        return indexer.get(sections[section]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 1;
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

}

