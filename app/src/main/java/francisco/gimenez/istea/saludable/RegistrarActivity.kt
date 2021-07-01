package francisco.gimenez.istea.saludable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.PhantomReference

class RegistrarActivity : AppCompatActivity() {
    private var sexos:ArrayList<String> = ArrayList<String>()
    private var tratamientos:ArrayList<String> = ArrayList<String>()

    private lateinit var txtName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar:ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth

    private lateinit var txtDni:EditText
    private lateinit var dateBirth:EditText
    private lateinit var txtLocalidad:EditText
    private lateinit var sexSpinner:Spinner
    private lateinit var treatmentSpinner:Spinner



    /*Todo:Agregar todo esto:
    * DNI
    * Fecha de nac.
    * Localidad
    * Sexo(spinner)
    * Tratamiento(spinner)
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        //Initializing variables.
        Initialize()

        database = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")





    }

    private fun Initialize(){

        txtName = findViewById(R.id.txtName)
        txtLastName = findViewById(R.id.txtLastName)
        txtPassword = findViewById(R.id.txtPassword)
        txtEmail = findViewById(R.id.txtEmail)
        txtDni = findViewById(R.id.txtDni)
        dateBirth = findViewById(R.id.dateNacimiento)
        txtLocalidad = findViewById(R.id.txtLocalidad)
        progressBar = findViewById(R.id.progressBar)
        sexSpinner = findViewById(R.id.spinnerSexo)
        treatmentSpinner = findViewById(R.id.spinnerTratamiento)


        sexos.add("Masculino")
        sexos.add("Femenino")
        sexos.add("Otro")

        tratamientos.add("Obesidad")
        tratamientos.add("Anorexia")
        tratamientos.add("Bulimia")

        InitSpinner(sexos,sexSpinner)
        InitSpinner(tratamientos,treatmentSpinner)
    }


    fun register(view: View){
        createNewAccount()
    }
    private fun createNewAccount(){
        val name:String = txtName.text.toString()
        val lastName:String = txtLastName.text.toString()
        val email:String = txtEmail.text.toString()
        val password:String = txtPassword.text.toString()

        if(
                !TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(lastName)
                && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)
        ){
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task ->
                            if(task.isComplete){
                                val user:FirebaseUser? = auth.currentUser
                                VerificarEmail(user)

                                val userDB = dbReference.child(user?.uid.toString())
                                userDB.child("name").setValue(name)
                                userDB.child("lastName").setValue(lastName)
                                userDB.child("dni").setValue(txtDni.text.toString())
                                userDB.child("birth").setValue(dateBirth.text.toString())
                                userDB.child("localidad").setValue(txtLocalidad.text.toString())
                                userDB.child("sexo").setValue(sexSpinner.selectedItem.toString())
                                userDB.child("treatment").setValue(treatmentSpinner.selectedItem.toString())


                                //TODO: Agregar que se envien el resto de los datos a firebase.
                                /*
                                * DNI
                                * Fecha de nac.
                                * Localidad
                                * Sexo(spinner)
                                * Tratamiento(spinner)
                                */
                                EnviarALogin()

                            }
                    }

        }
    }

    private fun EnviarALogin(){
        startActivity(Intent(this,LogInActivity::class.java))
    }
    private fun VerificarEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
                ?.addOnCompleteListener(this){
                    task -> if(task.isComplete){
                    Toast.makeText(this, "Correo enviado!", Toast.LENGTH_SHORT).show()
                    }
                    else
                    Toast.makeText(this, "Error al enviar el correo!", Toast.LENGTH_SHORT).show()

                }
    }

    private fun InitSpinner(Items:ArrayList<String>, toInitialize:Spinner){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,Items)
        toInitialize.adapter = adapter
    }
}