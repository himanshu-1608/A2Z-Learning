package com.himanshu.a2zlearning.handler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.himanshu.a2zlearning.R;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CourseTestsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> topics;
    private Map<String,List<String>> map;

    CourseTestsAdapter(Context context, List<String> topics, Map<String, List<String>> map) {
        this.context = context;
        this.topics = topics;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return topics.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(map.get(topics.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return topics.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(map.get(topics.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*3 + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String heading = (String) getGroup(groupPosition);
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.explist_tests_parent,null);
        }
        TextView txtParent = convertView.findViewById(R.id.list_topics);
        txtParent.setText(heading);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String desc = (String) getChild(groupPosition,childPosition);
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.explist_tests_child,null);
        }
        TextView txtChild = convertView.findViewById(R.id.list_desc);
        txtChild.setText(desc);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
