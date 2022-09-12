package com.shante.countrydirectory.presentation.ui.countrylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shante.countrydirectory.R
import com.shante.countrydirectory.databinding.CountryCardBinding
import com.shante.countrydirectory.domain.Country
import java.lang.reflect.Type
import kotlin.reflect.typeOf

class CountryListAdapter(
    private val onClick: (country: Country) -> Unit
) : ListAdapter<Country, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Country -> R.layout.country_card
            else -> error("idk what it is item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.country_card -> CountryViewHolder.from(parent, onClick)
            else -> error("View holder doesn't exist")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CountryViewHolder -> holder.bind(getItem(position))
        }
    }
}

class CountryViewHolder(
    private val binding: CountryCardBinding,
    private val onClick: (country: Country) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {
        binding.countryCard.setOnClickListener { onClick(country) }
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

    companion object {
        fun from(parent: ViewGroup, onClick: (country: Country) -> Unit): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CountryCardBinding.inflate(inflater, parent, false)
            return CountryViewHolder(binding, onClick)
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