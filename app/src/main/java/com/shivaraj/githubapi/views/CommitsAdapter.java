package com.shivaraj.githubapi.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivaraj.githubapi.R;
import com.shivaraj.githubapi.models.AuthorItemModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by H237872 on 5/2/2017.
 */

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitsViewHolder> {

    Map<Integer,AuthorItemModel> modelsForList;

   public CommitsAdapter(Map<Integer,AuthorItemModel> modelsForList){
       this.modelsForList = modelsForList;
   }

    @Override
    public CommitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.commits_item, parent, false);
       return new CommitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommitsViewHolder holder, int position) {
       holder.name.setText("shiv");
    }

    @Override
    public int getItemCount() {
        return modelsForList.size();
    }

    public class CommitsViewHolder extends RecyclerView.ViewHolder{

       @BindView(R.id.profPicIv)
       public ImageView profPhoto;
        @BindView(R.id.nameTv)
       TextView name;
        @BindView(R.id.msgTv)
       TextView messages;

        public CommitsViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
