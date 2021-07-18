package matheusrodrigues.androidapps.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_detail.*
import matheusrodrigues.androidapps.dogs.R
import matheusrodrigues.androidapps.dogs.model.DogBreed
import matheusrodrigues.androidapps.dogs.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0
    private var currentDog: DogBreed? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)
        obsererViewModel()
    }

    private fun obsererViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dog ->
            currentDog= dog
                dog?.let {
                dogName.text = dog.dogBreed
                dogPurpose.text = dog.bredFor
                dogTemperament.text = dog.temperament
                dogLifespan.text = dog.lifeSpan
            }
        })
    }
}