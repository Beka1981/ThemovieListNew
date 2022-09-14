package ge.gogichaishvili.themovielist.main.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.gogichaishvili.themovielist.databinding.LayoutMovieItemBinding
import ge.gogichaishvili.themovielist.main.domain.model.ItemList

class ItemsAdapter(private val itemList: MutableList<ItemList>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemClickListener: (ItemList, Int) -> Unit

    fun setOnItemCLickListener(clickListener: (ItemList, Int) -> Unit) {
        itemClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(itemList_: List<ItemList>) {
        itemList.clear()
        itemList.addAll(itemList_)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: LayoutMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(itemList: ItemList) {
            binding.tvMovieName.text = itemList.originalTitle
            binding.ivPoster.setImageURI("https://image.tmdb.org/t/p/w500${itemList.posterPath}")
            binding.tvRating.text = itemList.voteAverage.toString()
            binding.tvReleaseDate.text = itemList.releaseDate
            binding.root.setOnClickListener {
                itemClickListener.invoke(itemList, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MoviesViewHolder).bindData(itemList[position])
    }
}