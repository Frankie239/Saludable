package francisco.gimenez.istea.saludable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import francisco.gimenez.istea.saludable.Model.User

class MainActivity : AppCompatActivity() {

    lateinit var userName:TextView
    lateinit var auth:FirebaseAuth
    lateinit var button:Button

    //Todo: Hacer que aca se muestre toda la info de los registros (recyclerview)
    //TODO: Hacer que no se desloguee una vez que cerras la app(podran?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        userName = findViewById(R.id.txtUsername)
        button = findViewById(R.id.buttonCrearRegistro)


        //val user:FirebaseUser? = auth.currentUser

        userName.text = User.uId.toString()
    }
    fun CreateMeal(view: View){
        startActivity(Intent(this,AddActivity::class.java))
    }
}