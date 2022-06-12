package com.example.flappyfish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Player> arrContact;

    public PlayerAdapter(@NonNull Context context, int resource, @NonNull List<Player> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrContact = objects;
    }

    public class ViewHolder {
        TextView tvPlayerName, tvOrder, tvScore;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_items, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvPlayerName = (TextView) convertView.findViewById(R.id.tvPlayerName);
            viewHolder.tvScore = (TextView) convertView.findViewById(R.id.tvScore);
            viewHolder.tvOrder = (TextView) convertView.findViewById(R.id.tvOrder);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Player contact = arrContact.get(position);
        viewHolder.tvPlayerName.setText(contact.getPlayerName());
        viewHolder.tvScore.setText(""+contact.getScore());
        viewHolder.tvOrder.setText(String.valueOf(position +1));

        return convertView;
    }
}
