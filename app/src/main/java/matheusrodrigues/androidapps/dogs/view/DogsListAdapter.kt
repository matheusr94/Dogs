package matheusrodrigues.androidapps.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dog.view.*
import matheusrodrigues.androidapps.dogs.R
import matheusrodrigues.androidapps.dogs.databinding.ItemDogBinding
import matheusrodrigues.androidapps.dogs.model.DogBreed
import matheusrodrigues.androidapps.dogs.util.getProgressDrawable
import matheusrodrigues.androidapps.dogs.util.loadImage

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickListener {

    fun updateDogsList(newDogsList: List<DogBreed>){
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogsList[position]
        holder.view.listener = this
    }

    override fun onDogClicked(v: View) {
        val uuid = v.dogId.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}