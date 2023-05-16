package activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.letu.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvSignin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.SignUp.setOnClickListener {
            val email = binding.registerlogin.text.toString()
            val pass = binding.registerpass.text.toString()
            val confirm = binding.etConfirm.text.toString()

            // Validasi email
            if (email.isEmpty()){
                binding.registerlogin.error = "e-mail is required"
                binding.registerlogin.requestFocus()
                return@setOnClickListener
            }

            // Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.registerlogin.error = "Invalid e-mail"
                binding.registerlogin.requestFocus()
                return@setOnClickListener
            }

            // Validasi Password
            if (pass.isEmpty()){
                binding.registerpass.error = "Password Required"
                binding.registerpass.requestFocus()
                return@setOnClickListener
            }

            // Validasi panjang password
            if (pass.length < 6 ){
                binding.registerpass.error = "Password minimum 6 characters"
                binding.registerpass.requestFocus()
                return@setOnClickListener
            }

            // Validasi Confirm Password
            if (confirm.isEmpty()){
                binding.etConfirm.error = "Password Required"
                binding.etConfirm.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,pass,confirm)

        }
    }

    private fun RegisterFirebase(email: String, pass: String, confirm: String) {
        // Jika semua terisi
        if (email.isNotEmpty() && pass.isNotEmpty() && confirm.isNotEmpty()) {
            // Jika password dan confirm password sama
            if (pass == confirm){
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Successful Registration", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this , MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this , "${it.exception?.message}" , Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this , "Password is not matching" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}