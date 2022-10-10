package com.example.loginextassignment.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.loginextassignment.databinding.VouchersSectionLayoutBinding

class VoucherCounntSection (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr){

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private var binding : VouchersSectionLayoutBinding

    init {
        binding = VouchersSectionLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    var voucherCount : Int ?= null
    set(value) {
        if (value == null)
            return

        field = value
        field.let {  count ->
            binding.voucherCountTV.text = "You have $count active vouchers"
        }
    }
}