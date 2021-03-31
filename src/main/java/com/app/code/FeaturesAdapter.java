package com.app.code;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

public class FeaturesAdapter extends RecyclerView.Adapter<FeaturesAdapter.ViewHolder> implements View.OnClickListener
       //implements Filterable
{
    public Context mContext;
    private PlacesClient mPlacesClient;
    public final List<Featureslist_method> featureslist;
   // public final List<Featureslist_method> featureslistfull;
    public FeaturesAdapter(Context mContext, List<Featureslist_method>featuresList)
    {
        this.mContext = mContext;
        this.featureslist = featuresList;
       // featureslistfull  = new ArrayList<>(featureslist);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int ViewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitesfeatures_list,parent,false);
        ImageView image=view.findViewById(R.id.image_dire);
      image.setOnClickListener(this);
      TextView rating = view.findViewById(R.id.text_tap_rate);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RatingActivity.class);
                mContext.startActivity(intent);
            }
        });
        ImageView phone =view.findViewById(R.id.image_phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 0708654469"));
                mContext.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Featureslist_method Featuresdetails = featureslist.get(position);
        holder.Sname.setText(Featuresdetails.getName());
        holder.Sdescription.setText(Featuresdetails.getDescription());
        holder.Sprice.setText(Featuresdetails.getPrice());
        Glide.with(mContext).load(Featuresdetails.getUrl()).into(holder.tvimage);

    }
    @Override
    public int getItemCount()
    {
        return featureslist.size();
    }

    @Override
    public void onClick(View v) {
       String geoUri = "http://maps.google.com/maps?q=loc:" + "NyeriMuseum";
        Intent intent = new Intent(mContext, Get_Direct.class);
        mContext.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView Sname,Sprice,Sdescription;
        ImageView tvimage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Sname = itemView.findViewById(R.id.tvName);
            Sprice = itemView.findViewById(R.id.tvprice);
            Sdescription = itemView.findViewById(R.id.tvdescription);
            tvimage=itemView.findViewById(R.id.tvimage);

        }
    }



      /*// @Override
    public Filter getFilter()
       {
           return featureslistFilter;
       }
       private Filter featureslistFilter = new Filter() {
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {
              List<Featureslist_method> filteredList = new ArrayList<>();
              if (charSequence == null || charSequence.length() == 0){
                  filteredList.addAll(featureslistfull);
              }else {
                  String filterPattern = charSequence.toString().toLowerCase().trim();
                  for (Featureslist_method item : featureslistfull){
                      if (item.getName().toLowerCase().contains(filterPattern)){
                          filteredList.add(item);
                      }
                  }
              }
              FilterResults results = new FilterResults();
              results.values = filteredList;
              return results;
           }

           @Override
           protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            featureslist.clear();
            featureslist.addAll((List)filterResults.values);
            notifyDataSetChanged();
           }
       };*/

}
