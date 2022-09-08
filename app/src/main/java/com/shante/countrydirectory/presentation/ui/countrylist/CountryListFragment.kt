package com.shante.countrydirectory.presentation.ui.countrylist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.FontCallback.getHandler
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shante.countrydirectory.R
import com.shante.countrydirectory.databinding.CountryListFragmentBinding
import com.shante.countrydirectory.domain.Country
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private val viewModel: CountryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigateToCountryDetailsScreen.observe(this) { country ->
            val direction =
                CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(
                    country
                )
            findNavController().navigate(direction)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CountryListFragmentBinding.inflate(layoutInflater, container, false).also { binding ->

        val adapter = CountryListAdapter { country: Country ->
            viewModel.onCountryCardClicked(country)
        }

        binding.countriesRecyclerView.adapter = adapter

        viewModel.getAllCountries()

        viewModel.countryList.observe(viewLifecycleOwner) { countryList ->
            adapter.submitList(countryList) //todo scrolling
            when (countryList.size) {
                0 -> binding.noResultsImage.visibility = View.VISIBLE
                else -> binding.noResultsImage.visibility = View.GONE
            }
        }

        binding.countryNameEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                val countryName = binding.countryNameEditText.text
                with(binding.clearEditTextButton) {
                    isEnabled = if (countryName.isNotEmpty()) {
                        setIconResource(R.drawable.ic_clear_edit_text)
                        true
                    } else {
                        setIconResource(R.drawable.ic_search)
                        false
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {
                viewModel.getFilteredList(arg0.toString())
            }
        })

        binding.clearEditTextButton.setOnClickListener {
            binding.countryNameEditText.text.clear()
            viewModel.countryList.value = viewModel.data
        }


    }.root


}