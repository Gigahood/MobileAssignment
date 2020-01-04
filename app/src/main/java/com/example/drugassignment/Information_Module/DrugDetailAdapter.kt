package com.example.drugassignment.Information_Module

import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drugassignment.Class.DrugDetail
import com.example.drugassignment.FIreStore.FirestoreAdapter
import com.example.drugassignment.R
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.infor_layout_main.view.*

open class DrugDetailAdapter(query: Query?, private val mListener: OnRestaurantSelectedListener) : FirestoreAdapter<DrugDetailAdapter.ViewHolder?>(query) {

    interface OnRestaurantSelectedListener {
        fun onRestaurantSelected(restaurant: DocumentSnapshot?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.infor_layout_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), mListener, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(snapshot: DocumentSnapshot, listener: OnRestaurantSelectedListener?, position: Int) {
            val drug = snapshot.toObject(DrugDetail::class.java)


            val resources = itemView.resources

            // Load image
//            Glide.with(itemView.restaurant_item_image.context)
//                .load(restaurant!!.photo)
//                .into(itemView.restaurant_item_image)
//            itemView.restaurant_item_name.text = restaurant.name
//            itemView.restaurant_item_rating.rating = restaurant.avgRating.toFloat()
//            itemView.restaurant_item_city.text = restaurant.city
//            itemView.restaurant_item_category.text = restaurant.category
//            itemView.restaurant_item_num_ratings.text = resources.getString(R.string.fmt_num_ratings,
//                restaurant.numRatings)
//            itemView.restaurant_item_price.text = RestaurantUtil.getPriceString(restaurant)
            itemView.InfoMain.text = drug?.DrugName
            itemView.infoIndex.text = position.toString()

            // Click listener
            itemView.setOnClickListener { listener?.onRestaurantSelected(snapshot) }
        }
    }
}