package francisco.gimenez.istea.saludable.Model

import com.google.firebase.auth.FirebaseUser

class User {

    companion object {

        var uId:String = ""
        var name:String = ""
        var meals: ArrayList<Meal> = ArrayList<Meal>()

        public fun GetUserInfo(user:FirebaseUser?){
            this.uId = user?.uid.toString()
            this.name = user?.displayName.toString()
        }


    }
}
