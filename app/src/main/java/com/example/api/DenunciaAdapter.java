package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DenunciaAdapter extends ArrayAdapter<Report> {

    AdapterButtonsListener listener;

    public interface AdapterButtonsListener {
        void onButtonEditClickListener(int position, Report denuncia);
        void onButtonDeleteClickListener(int position, Report denuncia);
    }

    public void setListener(AdapterButtonsListener listener) {
        this.listener = listener;
    }

    private ArrayList<Report> denuncia;
    private Context context;

    public DenunciaAdapter(Context context, ArrayList<Report> denuncia) {
        super(context, R.layout.listalayout, denuncia);
        this.context = context;
        this.denuncia = denuncia;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listalayout, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = convertView.findViewById(R.id.labelReporte);
            viewHolder.btnDetalle = convertView.findViewById(R.id.btnDetalle);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Report denuncia = getItem(position);

        viewHolder.textViewTitle.setText(denuncia.getTitle());


        viewHolder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onButtonEditClickListener(position, denuncia);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView textViewTitle;
        Button btnDetalle;
    }
}
