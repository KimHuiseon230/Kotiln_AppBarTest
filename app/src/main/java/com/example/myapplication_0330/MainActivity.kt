package com.example.myapplication_0330

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_0330.databinding.ActivityMainBinding
import com.example.viewpagertablayoutpro.CustomRecycleAdapter
import com.example.viewpagertablayoutpro.DataList
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var dataList: MutableList<DataList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.navigation.setNavigationItemSelectedListener(this)
        //+++ toolbar
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        //+++ toolbar

        // ++++ dataList 설정해서 리사이클러 뷰에 값 넣기 start
        dataList = mutableListOf<DataList>()

        for (i in 1..20){
            if(i%2==1){
                dataList.add(DataList("홍길동${i}","${20+i}","koreaLove1${10+i}@naver.com",R.drawable.man))
            }else{
                dataList.add(DataList("고길동${i}","${20+i}","koreaHate2${20+i}@naver.com",R.drawable.woman))
            }
        } //for end

        binding.recyclerView.adapter = CustomRecycleAdapter(dataList)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,1 ))
        // ++++ dataList 설정해서 리사이클러 뷰에 값 넣기 End

    } // MainActivity End

    // 메뉴에서 토글 버튼을 눌렀을 때 네비게이션 바를 활성화 시켜서 오른쪽, 왼쪽으로 움직일 수 있도록 한다.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_user -> Toast.makeText(this,"User Data clicked", Toast.LENGTH_SHORT).show()
            R.id.item_info -> Toast.makeText(this,"App Data clicked", Toast.LENGTH_SHORT).show()
            R.id.item_age-> Toast.makeText(this,"User Age clicked", Toast.LENGTH_SHORT).show()
            R.id.item_email-> Toast.makeText(this,"User Email clicked", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}