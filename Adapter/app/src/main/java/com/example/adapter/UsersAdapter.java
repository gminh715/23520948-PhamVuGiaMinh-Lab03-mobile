package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(@NonNull Context context, @NonNull List<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User currentUser = getItem(position);
        if (currentUser != null) {
            holder.tvName.setText(currentUser.getName());
            holder.tvHome.setText(currentUser.getHometown());
        }

        return convertView;
    }

    static class ViewHolder {
        final TextView tvName;
        final TextView tvHome;

        ViewHolder(View itemView) {
            tvName = itemView.findViewById(R.id.tvName);
            tvHome = itemView.findViewById(R.id.tvHome);
        }
    }
}

