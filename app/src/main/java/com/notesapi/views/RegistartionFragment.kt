package com.notesapi.views

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.livedata.app.R
import com.livedata.app.databinding.FragmentRegistartionBinding
import com.notesapi.modals.UserRequest
import com.notesapi.utils.NetworkResult
import com.viewmodal.AuthViewmodal
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

@AndroidEntryPoint
class RegistartionFragment : Fragment() {
    var _binding: FragmentRegistartionBinding? = null;
    val binding get() = _binding!!


    private val viewmodal by activityViewModels<AuthViewmodal>()


//      @Inject
//      lateinit var viewmodal: AuthViewmodal

//    private var _authViewmodal: AuthViewmodal? = null;
//    val authview get() = _authViewmodal!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistartionBinding.inflate(inflater, container, false)
        //  viewmodal = ViewModelProvider(requireActivity()).get(AuthViewmodal::class.java)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.donthaveText.setOnClickListener {
            findNavController().navigate(R.id.action_registartionFragment2_to_loginfragment2)
        }

        binding.regButtonTxt.setOnClickListener {
            //findNavController().navigate(R.id.action_registartionFragment2_to_homeFragment2)
            //   binding.progressBarCyclic.visibility=View.VISIBLE;
            var validDate = validateUserdetails();
            if (!validDate.first) {
                binding.errorTxt.text = validDate.second;
            } else {
                viewmodal.signupUser(getUserDetails())
            }
        }

        bindObserver();

    }

    private fun getUserDetails(): UserRequest {
        return UserRequest(
            binding.emailEdt.text.toString(),
            binding.passEdt.text.toString(),
            binding.nameEdt.text.toString()
        );
    }

    private fun validateUserdetails(): Pair<Boolean, String> {
        var userDetails = getUserDetails();
        return viewmodal.validDateUser(
            UserRequest(
                userDetails.email, userDetails.password,
                userDetails.username
            )
        )

    }

    private fun bindObserver() {
        viewmodal.userResponseLiveData.observe(viewLifecycleOwner,
            {
                binding.progressBarCyclic.visibility = View.GONE;
                when (it) {
                    is NetworkResult.Success -> {
                        findNavController().navigate(R.id.action_registartionFragment2_to_homeFragment2)
                    }
                    is NetworkResult.Error -> {
                        val toast = Toast.makeText(
                            context,
                            "Error", Toast.LENGTH_SHORT
                        );
                        toast.show();
                    }
                    is NetworkResult.Loading -> {

                    }

                }
            })
    }
}