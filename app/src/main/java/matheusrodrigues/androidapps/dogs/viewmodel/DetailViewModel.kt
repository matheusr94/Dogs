package matheusrodrigues.androidapps.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import matheusrodrigues.androidapps.dogs.model.DogBreed
import matheusrodrigues.androidapps.dogs.model.DogDatabase
import java.util.*

class DetailViewModel(application: Application): BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int){
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }

    }

}