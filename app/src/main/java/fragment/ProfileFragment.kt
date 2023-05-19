package fragment

import activity.MainActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.letu.R
import com.example.letu.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AlertDialog
import dataclass.Main
import org.w3c.dom.Text


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        // Logout
        val logout = binding.logout
        logout.setOnClickListener {
            val mDialogBuilder = AlertDialog.Builder(requireContext(), R.style.TransparentDialog)
            val inflater = layoutInflater
            val mDialogView = inflater.inflate(R.layout.logout_alert, null)
            mDialogBuilder.setView(mDialogView)
            val mDialog = mDialogBuilder.create()
            mDialog.show()

            val yes = mDialogView.findViewById<TextView>(R.id.buttonyes)
            yes.setOnClickListener{
                firebaseAuth.signOut()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }

            val no = mDialogView.findViewById<TextView>(R.id.buttonno)
            no.setOnClickListener{
                // Tutup dialog saat tombol "no" diklik
                mDialog.dismiss()
            }
        }
        return binding.root
    }

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}