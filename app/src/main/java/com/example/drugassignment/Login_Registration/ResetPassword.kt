package com.example.drugassignment.Login_Registration


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.drugassignment.R
import com.example.drugassignment.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class ResetPassword : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_reset_password, container, false)

        binding.btnReset.setOnClickListener {
            resetPassword()
        }
        setHasOptionsMenu(false)

        binding.linear3.setOnClickListener {
            hideKeyboard()
        }

        return binding.root
    }

    // hide option bar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }

    private fun resetPassword() {
        val emailAddress = binding.editText.text.toString()
        val auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Reset", "Email sent.")
                    Toast.makeText(activity, "A Reset Password Had Sent To You Email.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Fail.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun hideKeyboard() {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view?.windowToken,0)
    }

}
