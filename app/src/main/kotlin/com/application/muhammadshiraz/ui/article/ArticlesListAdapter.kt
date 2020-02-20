package com.application.muhammadshiraz.ui.article

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.application.muhammadshiraz.R
import com.application.muhammadshiraz.databinding.ItemArticleBinding
 import com.application.muhammadshiraz.model.Article

class ArticlesListAdapter(var clickListener: (Article) -> Unit): RecyclerView.Adapter<ArticlesListAdapter.ViewHolder>() {
    private lateinit var articleList:List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_article, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return if(::articleList.isInitialized) articleList.size else 0
    }

    fun updateArticleList(articleList:List<Article>){
        this.articleList = articleList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArticleBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = ArticleViewModel()

        fun bind(article: Article, clickListener: (Article) -> Unit){
            viewModel.bind(article)
            binding.viewModel = viewModel
            binding.itemCell.setOnClickListener {
                (clickListener(article))
            }
        }
    }
}