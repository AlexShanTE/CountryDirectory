package com.shante.countrydirectory.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shante.countrydirectory.R
import com.shante.countrydirectory.databinding.CountryCardBinding
import com.shante.countrydirectory.domain.Country
import kotlinx.coroutines.withContext

class CountryListAdapter(
    private val countryInteractionListener: CountryListInteractionListener
) : ListAdapter<Country, CountryListAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, countryInteractionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: CountryCardBinding,
        private val listener: CountryListInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var country: Country

        init {
            itemView.setOnClickListener { listener.onCountryCardClicked(country) }
        }

        fun bind(country: Country) {
            this.country = country
            with(binding) {
                Glide.with(this.flag)
                    .asDrawable()
                    .centerCrop()
                    .load(country.flags.png)
                    .error(R.mipmap.ic_launcher)
                    .into(flag)
                countryName.text = "${country.name}(${country.alpha2Code})"
                capitalName.text = country.capital ?: binding.root.context.getString(R.string.none)
            }
        }
    }

}

private object DiffCallBack : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}