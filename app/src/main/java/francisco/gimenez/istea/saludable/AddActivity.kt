package francisco.gimenez.istea.saludable

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import francisco.gimenez.istea.saludable.Model.User
import java.time.LocalDateTime

class AddActivity : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()
    lateinit var txtFirstDish:EditText
    lateinit var txtSecondDish:EditText
    lateinit var btnSave:Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        txtFirstDish = findViewById(R.id.txtFirstDish)
        txtSecondDish = findViewById(R.id.txtSecondDish)
        btnSave = findViewById(R.id.SaveButton)

        btnSave.setOnClickListener {
            GuardarRegistro()
        }


    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun  GuardarRegistro(){
        val currentDateTime = LocalDateTime.now()
        try {
            db.collection("Users").document(User.uId)
                .collection("Registros")
                .document(currentDateTime.toString())
                .set(
                    hashMapOf(
                        "primer_plato" to txtFirstDish.text.toString(),
                        "segundo_plato" to txtSecondDish.text.toString()
                    )
                )
        }
        catch (e:Exception){
            Log.e("Error", e.message.toString())
        }



    }

}