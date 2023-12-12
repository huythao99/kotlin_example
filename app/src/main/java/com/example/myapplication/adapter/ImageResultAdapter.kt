package com.example.myapplication.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.OverlayView
import com.example.myapplication.R
import com.example.myapplication.models.ImageResult
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class ImageResultAdapter(
    private val list: Array<ImageResult>,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<ImageResultAdapter.ImageResultViewHolder>() {

    class ImageResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Find all the views of the list item
        private val advantage: TextView = itemView.findViewById(R.id.advantage)
        private val weakness: TextView = itemView.findViewById(R.id.weakness)
        private val posture: TextView = itemView.findViewById(R.id.posture)
        private val imageResult: ImageView = itemView.findViewById(R.id.image_result)
        private val indicator: TextView = itemView.findViewById(R.id.indicator)
        private val btn: MaterialButton = itemView.findViewById(R.id.exercise_btn)
        private val overlay: OverlayView = itemView.findViewById(R.id.overlay);

        // Show the data in the views
        fun bind(repo: ImageResult, position: Int, maxNum: Int, onClick: () -> Unit) {
            val advantageDes = repo.advantage
            val weaknessDes = repo.weakness
            val postureDes = repo.posture
            val uri = Uri.parse(repo.uri)
            advantage.text = "Posture: $advantageDes"
            weakness.text = "Weakness: $weaknessDes"
            posture.text = "Posture:$postureDes"
            Picasso.get().load(uri).into(imageResult)
            indicator.text = "${position + 1} / $maxNum"
            overlay.setResults(repo.result, repo.height, repo.width)
            if (position == maxNum - 1) {
                btn.visibility = View.VISIBLE
                btn.setOnClickListener {
                    onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageResultViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.image_result_view, parent, false)
        return ImageResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageResultViewHolder, position: Int) {
        holder.bind(list!![position], position, list.size, onClick)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}