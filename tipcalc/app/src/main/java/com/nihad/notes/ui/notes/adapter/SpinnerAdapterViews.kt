package com.nihad.notes.ui.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import com.nihad.notes.R
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.model.MySpinnerItem
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import com.skydoves.powerspinner.PowerSpinnerInterface
import com.skydoves.powerspinner.PowerSpinnerView


class SpinnerAdapterViews(
    powerSpinnerView: PowerSpinnerView
) : RecyclerView.Adapter<SpinnerAdapterViews.MySpinnerViewHolder>(),
    PowerSpinnerInterface<MySpinnerItem> {


    private  var spinnerItems: MutableList< MySpinnerItem> = arrayListOf()


    inner class MySpinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var composeView: ComposeView = itemView.findViewById(R.id.compose_view)


    }

    override val spinnerView: PowerSpinnerView = powerSpinnerView

    override var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener<MySpinnerItem>? = null

    override fun onBindViewHolder(holder: MySpinnerViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            notifyItemSelected(position)
        }
        holder.composeView.setContent {
            TipcalcTheme {


                Texts(text = spinnerItems[position])


            }
        }
    }

    @Composable
    private fun Texts(text: MySpinnerItem) {
        Surface(modifier = Modifier.wrapContentHeight(),color = Color.Transparent) {
            Text(text = text.comboOption,modifier = Modifier.wrapContentHeight().padding(8.dp),
                color = TipcalcTheme.colors.backgroundreverse)
        }
    }

    // we must call the spinnerView.notifyItemSelected method to let PowerSpinnerView know about changed information.
    override fun notifyItemSelected(index: Int) {
        this.spinnerView.notifyItemSelected(index, this.spinnerItems[index].comboOption)
        this.onSpinnerItemSelectedListener?.onItemSelected(index, this.spinnerItems[index])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpinnerAdapterViews.MySpinnerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_column, parent, false)
        return MySpinnerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.spinnerItems.count()
    }

    override fun setItems(itemList: List<MySpinnerItem>) {
        this.spinnerItems.clear()
        this.spinnerItems.addAll(itemList)
        notifyDataSetChanged()
    }


}