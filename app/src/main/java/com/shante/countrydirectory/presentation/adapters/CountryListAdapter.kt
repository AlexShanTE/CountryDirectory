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
) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    private var countryList: List<Country>? = null

    fun setCountryList(countryList: List<Country>) {
        this.countryList = countryList
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return countryList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, countryInteractionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countryList?.get(position)?.let { holder.bind(it) }
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