package com.example.test.ui.specialty

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.data.db.Specialty
import com.example.test.databinding.SpecialtyListFragmentBinding
import com.example.test.ui.adapter.SpecialtyAdapter
import com.example.test.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtyListFragment : Fragment(R.layout.specialty_list_fragment) {

    private val viewModel: SpecialtyListViewModel by viewModels()

    private val binding by viewBinding(SpecialtyListFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSpecialties(::errorAction)
            .observe(
                this.viewLifecycleOwner
            ) { list -> if (list.isNotEmpty()) initList(list) }
    }

    private fun initList(specialties: List<Specialty>) {
        val adapter = SpecialtyAdapter(::onClick)
        adapter.submitList(specialties)
        binding.specialtyList.adapter = adapter
    }

    private fun onClick(specialty: Specialty) {
        val action = SpecialtyListFragmentDirections.employeeList(specialty.specialtyId)
        findNavController().navigate(action)
    }

    private fun errorAction() {
        Toast.makeText(requireContext(), resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }
}