package ge.gogichaishvili.themovielist.login.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.gogichaishvili.themovielist.databinding.FragmentLoginBinding
import ge.gogichaishvili.themovielist.login.presentation.viewmodels.LoginViewModel
import ge.gogichaishvili.themovielist.app.network.Resource
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.authFlow.collect {
                when (it) {

                    is Resource.Loading -> {
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Success -> {
                        println("token is ${it.data!!.request_token}")
                    }
                }
            }
        }


        lifecycleScope.launch {
            viewModel.loginFlow.collect {
                when (it) {

                    is Resource.Loading -> {
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Success -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }

        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            if (!it.isValid) {
                val message = String.format(resources.getString(it.errorMessageRes))
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            viewModel.onLogin(
                binding.etUsername.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}