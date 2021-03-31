package com.app.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TrialAdapter extends RecyclerView.Adapter<TrialAdapter.viewholder> implements Filterable {

    private Context mContext;
    private List<Featureslist_method> featureslist;
     private List<Featureslist_method> featureslistfull;
    private AdapterView.OnItemClickListener mListener;

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
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
    };
    public interface OnItemClickListener
    {

        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = (AdapterView.OnItemClickListener) listener;
    }
    public TrialAdapter (Context context, List<Featureslist_method> mfeatures){
        mContext = context;
        featureslist = mfeatures;
        featureslistfull = new ArrayList<>(mfeatures);
    }


    @NonNull
    @Override
    public TrialAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.sitesfeatures_list,parent,false);
        return new viewholder(v, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TrialAdapter.viewholder holder, int position) {
        Featureslist_method Featuresdetails = featureslist.get(position);
        holder.Sname.setText(Featuresdetails.getName());
        holder.Sdescription.setText(Featuresdetails.getDescription());
        holder.Sprice.setText(Featuresdetails.getPrice());
        Glide.with(mContext).load(Featuresdetails.getUrl()).into(holder.tvimage);
    }

    @Override
    public int getItemCount() {
        return featureslist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView Sname,Sprice,Sdescription;
        ImageView tvimage;
        public viewholder (@NonNull View itemView, final OnItemClickListener listener){
        super(itemView);

            Sname = itemView.findViewById(R.id.tvName);
            Sprice = itemView.findViewById(R.id.tvprice);
            Sdescription = itemView.findViewById(R.id.tvdescription);
            tvimage=itemView.findViewById(R.id.tvimage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }
}
