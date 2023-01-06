package com.example.flexit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flexit.nutrition_repository.food.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    private List<Food> foods;

    public FoodAdapter(List<Food> foods){
        this.foods = foods;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public Food food;
        public TextView foodNameTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            foodNameTextView = (TextView) itemView.findViewById(R.id.food_name_textbox);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View foodView = inflater.inflate(R.layout.food_recycler_item_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);

        TextView textView = holder.foodNameTextView;
        textView.setText(food.getFoodName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
