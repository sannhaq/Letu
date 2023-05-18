package activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import com.example.letu.R
import fragment.CartFragment
import fragment.HomeFragment

class PembayaranActivity : AppCompatActivity() {

    private lateinit var context: Context
    private var metodepembayaran = ""

    lateinit var ovo : View
    lateinit var gopay : View
    lateinit var dana : View

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // kembali ke cart fragment
        val backButton: ImageView = findViewById(R.id.backbutton)
        backButton.setOnClickListener {
            // Memulai fragment CartFragment saat tombol kembali diklik
            val cartFragment = CartFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, cartFragment)
                .commit()
            finish()
        }

        // Ambil data total harga dari intent
        val totalHarga = intent.getIntExtra("TOTAL_HARGA", 0)

        // Tampilkan total harga pada TextView
        val totalHargaTextView = findViewById<TextView>(R.id.order)
        totalHargaTextView.text = "Rp. $totalHarga"

        //Tampilkan Total summry
        val totalSummary = findViewById<TextView>(R.id.summary)
        var dev = (totalHarga + 2000)
        totalSummary.text = "Rp. $dev"

        context = this
        val toast4: Button = findViewById(R.id.submit)

        ovo = findViewById(R.id.ovoselect)
         gopay = findViewById(R.id.gopayselect)
         dana = findViewById(R.id.danaselect)
        val ovolayout : ConstraintLayout = findViewById(R.id.constraintLayout4)
        val gopaylayout : ConstraintLayout = findViewById(R.id.constraintLayout5)
        val danalayout : ConstraintLayout = findViewById(R.id.constraintLayout6)
        var saldo = 500000
        var sisa = saldo - dev

        toast4.setOnClickListener{
            showDialog()
            showToast("Metode pembayaran yang anda pilih " +metodepembayaran+ " sisa saldo anda $sisa")}


        ovolayout.setOnClickListener{
            refresh()
            metodepembayaran = "ovo"
            ovo.visibility = View.VISIBLE
        }
        gopaylayout.setOnClickListener{
            refresh()
            metodepembayaran = "gopay"
            gopay.visibility = View.VISIBLE
        }
        danalayout.setOnClickListener{
            refresh()
            metodepembayaran = "dana"
            dana.visibility = View.VISIBLE
        }
    }

    private fun showDialog() {
        val mDialog = AlertDialog.Builder(this, R.style.TransparentDialog)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.payment_dialog,null)
        mDialog.setView(mDialogView)
        mDialog.show()
        val back = mDialogView.findViewById<ConstraintLayout>(R.id.backContainerSuccses)
        back.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(context, message , Toast.LENGTH_SHORT)
        toast.show()
    }

    fun refresh() {
        ovo.visibility = View.INVISIBLE
        gopay.visibility = View.INVISIBLE
        dana.visibility = View.INVISIBLE

    }
}