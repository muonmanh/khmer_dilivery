package com.example.beniten.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Beniten on 3/13/2018.
 */

public class StickyListViewAdapter extends ArrayAdapter<String> implements SectionIndexer{

    Context context;
    int resource;
    List<String> elements;
    LayoutInflater inflater;
    private HashMap<String, Integer> indexer = new HashMap<String, Integer>();
    private String[] sections; ;
    private final String SEP_FLAG = "sepFlag";

    Row row;

    public StickyListViewAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.elements = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < objects.size(); i++){
            String element = objects.get(i);
            String ch = element.substring(0, 1);
            ch = ch.toUpperCase(Locale.US);

            // HashMap will prevent duplicates
            indexer.put(ch, i);

            Set<String> sectionLetters = indexer.keySet();

            // create a list from the set to sort
            ArrayList<String> sectionList = new ArrayList<String>(sectionLetters);

            Log.d("sectionList", sectionList.toString());
            Collections.sort(sectionList);

            sections = new String[sectionList.size()];

            sectionList.toArray(sections);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String value = elements.get(position);
        convertView = inflater.inflate(resource, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.title_text);

       // textView.setText("Header "+ sections[position]);
       // TextView tvContent = convertView.findViewById(R.id.tv_content);
       // tvContent.setText(value);

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int i) {
        Log.d("positionForSection",i+"");
        return indexer.get(sections[i]);
    }

    @Override
    public int getSectionForPosition(int i) {
        Log.d("position", "" + i);
        return 0;
    }

    class Row{
        TextView t;
        LinearLayout l;
    }


}
