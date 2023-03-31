package com.example.viewpagertablayoutpro

import android.os.Build.VERSION_CODES.O
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_0330.databinding.ItemViewBinding

class CustomRecycleAdapter(val dataList: MutableList<DataList>) :
    RecyclerView.Adapter<CustomRecycleAdapter.CustomViewHodler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHodler {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.e("CustomRecycleAdapter", " notify_CreateViewHolder")
        return CustomViewHodler(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CustomViewHodler, position: Int) {
        val binding = holder.binding
        binding.tvName.text = dataList.get(position).tvName
        binding.tvAge.text = dataList.get(position).tvAge
        binding.tvEmail.text = dataList.get(position).tvEmail
        binding.ivPicture.setImageResource(dataList.get(position).ivPicture)

        Log.e("CustomRecycleAdapter", "notify_onBindViewHolder")

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${dataList.get(position).toString()}",
                Toast.LENGTH_SHORT
            ).show()

            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "${dataList[position]}", Toast.LENGTH_SHORT)
                    .show()

            }

            binding.root.setOnLongClickListener(object : View.OnLongClickListener {
                override fun onLongClick(p0: View?): Boolean {
                    val position = holder.adapterPosition
                    val dataVO = dataList.removeAt(position)

                    Toast.makeText(binding.root.context, "  [ ${dataVO.tvName} ] 가 삭제되었습니다" , Toast.LENGTH_SHORT)
                        .show()
                    /*
                    변경 점 O -> 리스트의 크기와 아이템이 둘 다 변경 되는 경우에 사용. CustomAdapter
                    에게 프로그램으로 다시 그려줄 것을 요청
                    */
                    notifyDataSetChanged()
                    Log.e("CustomRecycleAdapter", "notify_setOnLongClickListener")
                    return true
                }
            })
        }

    }

    class CustomViewHodler(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}

