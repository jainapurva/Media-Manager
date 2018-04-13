package com.example.apurva.mediamanager.Menu_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apurva.mediamanager.R;

public class SettingsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_settings);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SettingsFragment.RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        //adapter for recycler view
//        setUpRecyclerView(recyclerView);
        return view;
    }

   /* private void setUpRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new adapter(rv.getContext(), "Facebook"));
        Log.d("Firebase-data", "user adapter");
    }*/


    //Adapter Class

    private class RecyclerAdapter extends RecyclerView.Adapter<SettingsFragment.RecyclerAdapter.ViewHolder> {

        private String[] setting_name={"General",
                "Notification","Data Sync","Privacy","About"};

        //private int[] image={R.drawable.ic_info_black_24dp,R.drawable.ic_settings_black_24dp};

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView name;
            private CardView card;



            public ViewHolder(View itemView) {
                super(itemView);
                //logo=(ImageView)itemView.findViewById(R.id.image_logo);
                name=(TextView)itemView.findViewById(R.id.setting_name);
                card = (CardView) itemView.findViewById(R.id.card_view_setting);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position=getAdapterPosition();

                        Toast.makeText(getContext(),"Click detected on card "+setting_name[position],
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }

        @Override
        public SettingsFragment.RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.recycler_card_settings, parent, false);
            return new SettingsFragment.RecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SettingsFragment.RecyclerAdapter.ViewHolder holder, int position) {
            holder.name.setText(setting_name[position]);
            //holder.logo.setImageResource(image[position]);
            /*holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), MediaActivity.class);
                    startActivity(intent);
                }
            });*/
        }
        @Override
        public int getItemCount() {
            return setting_name.length;
        }

    }


}
