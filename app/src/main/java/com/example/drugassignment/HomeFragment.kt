package com.example.drugassignment


import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.drugassignment.Class.DrugDetail
import com.example.drugassignment.Login_Registration.LoginViewModel
import com.example.drugassignment.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_nav_header2.view.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.text.Typography.times


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var navController : NavController
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        navController = activity!!.findNavController(R.id.nav_host_fragment)

        observeAuthenticationState()

//        binding.button2.setOnClickListener {
//            updateFirebase()
//        }

        return binding.root
    }

    private fun observeAuthenticationState(){
        // get the Nav view from the layout
        Log.i("Register", "Start")
        val navView : NavigationView = activity!!.findViewById(R.id.nav_view)
        // get the header view
//        val headerView  =
//            LayoutInflater.from(this).inflate(R.layout.activity_nav_header2, null)

        val headerView = navView.getHeaderView(0)
        val drawerLayout : DrawerLayout = activity!!.findViewById(R.id.drawer_layout)

        viewModel.authenticationState?.observe(this, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    //navView.removeHeaderView(headerView)
                    headerView.textViewDisplayName.text = viewModel.user.displayName
                    headerView.textViewHeaderEmail.text = viewModel.user.email
                    headerView.setOnClickListener {
                        // closing with animation
                        // rawerLayout.closeDrawers()
                        drawerLayout.closeDrawer(Gravity.LEFT, false)
                        navController.navigate(R.id.action_homeFragment_to_profile_Activity)
                    }
                    //navView.addHeaderView(headerView)
                }
                else -> {
                    // navView.removeHeaderView(headerView)
                    headerView.textViewDisplayName.text = "Name"
                    headerView.textViewHeaderEmail.text = "Email"
                    headerView.setOnClickListener {
                        drawerLayout.closeDrawer(Gravity.LEFT, false)
                        navController.navigate(R.id.action_homeFragment_to_login)
                    }
                    //  navView.addHeaderView(headerView)
                }

            }
        })
    }

//    private fun updateFirebase() {
//        val mFirestore = FirebaseFirestore.getInstance();
//        val restaurants = mFirestore.collection("DrugInfo")
//
//        val drugList : MutableList<DrugDetail>
//
//        val description =
//            "Adderall contains a combination of amphetamine and dextroamphetamine.  Amphetamine and dextroamphetamine are central nervous system stimulants  that affect chemicals in the brain and nerves that contribute to  hyperactivity and impulse control. Adderall is used to treat attention deficit hyperactivity disorder (ADHD) and  narcolepsy. Adderall may also be used for purposes not listed in this medication guide."
//
//        val sideEffect1 =
//            "Lowered immunity to illness" +
//        "Depression" +
//        "Chronic anxiety" +
//                "Reduced sperm count in men" +
//        "Sedation" +
//        "Slowed reaction times" +
//        "Enhanced senses, such as seeing brighter colors" +
//        "Impaired sense of time"
//
//
//
//
//
//        val DrugName =
//            listOf<String>("Marijuana leaves","Hashish","Hash oil","Sativex")
//            val type = "cannabis"
//
//
//
//        for (drug in DrugName) {
//            val a = DrugDetail(drug,description,sideEffect1,type)
//            restaurants.add(a)
//        }
//    }


}
