package com.example.apurva.mediamanager.Menu_fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apurva.mediamanager.MediaActivity;
import com.example.apurva.mediamanager.R;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    WebView webView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_home);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter();
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

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private String[] media_name={"Facebook",
        "Twitter"};

        private int[] image={R.drawable.images,R.drawable.unnamed};


        public class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView logo;
            private TextView name;
            private CardView card;



            public ViewHolder(View itemView) {
                super(itemView);
                logo=(ImageView)itemView.findViewById(R.id.image_logo);
                name=(TextView)itemView.findViewById(R.id.media_name);
                card = (CardView) itemView.findViewById(R.id.card_view_home);
                //webView=itemView.findViewById(R.id.webview);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position=getAdapterPosition();
                        /*switch (position){
                            case 0: webView.loadUrl("https://www.facebook.com");
                            return;

                            case 1: webView.loadUrl("https://www.twitter.com");
                            return;
                        }*/
                        Toast.makeText(getContext(),"Click detected on card "+media_name[position],
                              Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.recycler_card_home, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(media_name[position]);
            holder.logo.setImageResource(image[position]);
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
            return media_name.length;
        }

    }



}




