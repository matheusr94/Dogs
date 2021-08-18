package matheusrodrigues.androidapps.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import matheusrodrigues.androidapps.dogs.R
import matheusrodrigues.androidapps.dogs.model.DogBreed
import matheusrodrigues.androidapps.dogs.util.getProgressDrawable
import matheusrodrigues.androidapps.dogs.util.loadImage

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    fun updateDogsList(newDogsList: List<DogBreed>){
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogsList[position].dogBreed
        holder.view.lifespan.text = dogsList[position].lifeSpan
        holder.view.setOnClickListener{
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = dogsList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.loadImage(dogsList[position].imageUrl, getProgressDrawable(holder.view.imageView.context))
    }

    override fun getItemCount() = dogsList.size

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}