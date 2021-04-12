package com.example.calculator.ui.dialogs.length

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import com.example.calculator.R
import com.example.calculator.databinding.LengthUnitPickViewBinding
import com.example.calculator.ui.dialogs.base.BaseDialog

class LengthUnitDialog(private val viewGroup: ViewGroup): BaseDialog<LengthUnitPickViewBinding,LengthUnitViewModel>(LengthUnitPickViewBinding::inflate,LengthUnitViewModel::class.java){

	var adapter: LengthUnitAdapter? = null

	override fun onResume() {
		super.onResume()
		val params: WindowManager.LayoutParams? = dialog?.window?.attributes
		dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		params?.width = viewGroup.width
		params?.height = (viewGroup.height * 0.84).toInt()
		params?.gravity = Gravity.BOTTOM
		dialog?.window?.attributes = params
	}

	override fun setupUI() {
		super.setupUI()
		adapter = LengthUnitAdapter()
		viewModel.loadData()
		binding.lengthRecycler.adapter = adapter
		adapter?.addItemClickListener(object : LengthUnitAdapter.OnItemClickListener{
			override fun itemClickListener(item: String) {
				Log.e("TAG", "itemClickListener: $item")
			}
		})

		binding.cancelUnitLength.setOnClickListener{ dismiss() }
		observe()
	}

	private fun observe() {
		viewModel.lengthUnitList.observe(this){
			adapter?.listUnit = it
		}
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		dialog?.window?.attributes?.windowAnimations = R.style.dialogAnimation
	}

}