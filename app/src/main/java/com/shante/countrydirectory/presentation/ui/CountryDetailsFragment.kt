package com.shante.countrydirectory.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shante.countrydirectory.R
import com.shante.countrydirectory.databinding.CountryDetailsFragmentBinding
import com.shante.countrydirectory.presentation.viewModels.CountryListViewModel
import com.shante.countrydirectory.utils.getFormattedString

class CountryDetailsFragment : Fragment() {

    private val viewModel: CountryListViewModel by viewModels()

    private val args by navArgs<CountryDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CountryDetailsFragmentBinding.inflate(layoutInflater, container, false).also { binding ->

        with(binding) {
            countryName.text = args.country.name
            capitalName.text = getString(R.string.capital, args.country.capital ?: binding.root.context.getString(R.string.none))
            regionName.text = getString(R.string.region, args.country.region)
            subRegionName.text = getString(R.string.subRegion, args.country.subregion)
            population.text = getString(R.string.population, args.country.population.toString())
            area.text = getString(R.string.area, args.country.area.toString())
            languages.text =
                getString(R.string.languages, args.country.languages.getFormattedString())

            Glide.with(this.flag)
                .asDrawable()
                .centerCrop()
                .load(args.country.flags.png)
                .error(R.mipmap.ic_launcher)
                .into(flag)
        }

    }.root

}