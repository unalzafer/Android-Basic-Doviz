package unalzafer.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import unalzafer.com.Activity.WebActivity;
import unalzafer.com.Models.DovizModel;
import unalzafer.com.R;

public class DovizAdapter extends RecyclerView.Adapter<DovizAdapter.ViewHolder> implements Filterable {

    private ArrayList<DovizModel> dovizModelArrayList;
    Context context;

    CustomFilter filter;
    ArrayList<DovizModel> filterList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView tvDovizName,tvDovizSelling,tvDovizPercent;
        ImageView ivDovizImage,ivArrow;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            ivDovizImage=itemView.findViewById(R.id.ivDovizImage);
            tvDovizName=itemView.findViewById(R.id.tvDovizName);
            ivArrow=itemView.findViewById(R.id.ivArrow);
            tvDovizSelling=itemView.findViewById(R.id.tvDovizSelling);
            tvDovizPercent=itemView.findViewById(R.id.tvDovizPercent);
            context=itemView.getContext();

        }
    }
    public DovizAdapter(ArrayList<DovizModel> dovizModelArrayList) {
        this.dovizModelArrayList = dovizModelArrayList;
        this.filterList=dovizModelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_doviz, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DovizAdapter.ViewHolder viewHolder, final int i) {


            Glide.with(context).load(dovizModelArrayList.get(i).getImageLink()).into(viewHolder.ivDovizImage);
            viewHolder.tvDovizName.setText(dovizModelArrayList.get(i).getName());
            if(dovizModelArrayList.get(i).getArrow().equals("up"))
                viewHolder.ivArrow.setImageResource(R.drawable.ic_arrow_up);
            else if(dovizModelArrayList.get(i).getArrow().equals("down"))
                viewHolder.ivArrow.setImageResource(R.drawable.ic_arrow_down);

            viewHolder.tvDovizSelling.setText(dovizModelArrayList.get(i).getSelling());
            viewHolder.tvDovizPercent.setText(dovizModelArrayList.get(i).getPercentage());

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, WebActivity.class).putExtra("url",dovizModelArrayList.get(i).getDetailLink()));
                }
            });

    }

    @Override
    public int getItemCount() {
        return dovizModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            filter=new CustomFilter();
        }

        return filter;
    }


    //INNER CLASS
    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results=new FilterResults();

            if(constraint != null && constraint.length()>0)
            {
                constraint=constraint.toString().toUpperCase();

                ArrayList<DovizModel> filters=new ArrayList<DovizModel>();

                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getName().toUpperCase().contains(constraint))
                    {
                        filters.add(filterList.get(i));
                    }
                }

                results.count=filters.size();
                results.values=filters;

            }else
            {
                results.count=filterList.size();
                results.values=filterList;

            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            dovizModelArrayList=(ArrayList<DovizModel>) results.values;
            notifyDataSetChanged();
        }

    }

}
