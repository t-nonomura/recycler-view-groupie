package net.storehouse.nono.recyclerviewgroupie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import kotlinx.android.synthetic.main.activity_main.*
import net.storehouse.nono.recyclerviewgroupie.databinding.ItemBodyBinding
import net.storehouse.nono.recyclerviewgroupie.databinding.ItemHeaderBinding

class MainActivity : AppCompatActivity() {

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = adapter

        // 本来はAPIレスポンスの結果等をリストに
        val items = mutableListOf<BindableItem<*>>()
        for (i in 1 .. 100) {
            if (i % 3 == 1) items.add(HeaderItem("Header$i"))
            else items.add(BodyItem("body$i"))
        }
        adapter.update(items)
    }
}

class HeaderItem(private val text: String) : BindableItem<ItemHeaderBinding>() {
    override fun getLayout() = R.layout.item_header

    override fun bind(viewBinding: ItemHeaderBinding, position: Int) {
        viewBinding.headerText.text = text
    }
}

class BodyItem(private val text: String) : BindableItem<ItemBodyBinding>() {
    override fun getLayout() = R.layout.item_body
    override fun bind(viewBinding: ItemBodyBinding, position: Int) {
        viewBinding.bodyText.text = text
    }
}