package francisco.gimenez.istea.saludable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.common.api.internal.RegisterListenerMethod
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var textLoginPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        txtUser = findViewById(R.id.txtUser)
        textLoginPassword = findViewById(R.id.textLoginPassword)
        progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }
    fun forgotPassword(view: View){

    }

    fun register(view: View){
        startActivity(Intent(this,RegistrarActivity::class.java))
    }

    fun login(view: View){
        LogInUser()

    }
    private fun LogInUser(){
        val user:String = txtUser.text.toString()
        val password:String = textLoginPassword.text.toString()
        if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(password)){
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                    .addOnCompleteListener(this){
                        task -> if(task.isSuccessful){
                            EnviarPantallaPrincipal()
                        }
                        else
                            Toast.makeText(this, "ERROR en la autenticacion", Toast.LENGTH_SHORT).show()
                    }
        }
    }

    private fun EnviarPantallaPrincipal(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}