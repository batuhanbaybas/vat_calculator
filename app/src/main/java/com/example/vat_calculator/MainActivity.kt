package com.example.vat_calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vat_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{
            calculateVat()
        }

    }

    private fun calculateVat() {
        val inputPrice = binding.inputValueEditText.text.toString()
        val price = inputPrice.toDoubleOrNull()

        if (price == null){
            binding.onlyVat.text = ""
            binding.netPrice.text = ""
            return
        }

        val checkVat = when (binding.vatRadioGroup.checkedRadioButtonId) {
            R.id.vat_radioButton_1 -> 0.01
            R.id.vat_radioButton_2 -> 0.08
             else -> 0.18
        }

        val calculation = checkVat * price

        val finalValue = NumberFormat.getCurrencyInstance(Locale("tr" ,"TR")).format(calculation)

        val netPrice = price-calculation

        val netPriceFinal = NumberFormat.getCurrencyInstance(Locale("tr" ,"TR")).format(netPrice)

        binding.onlyVat.text = getString(R.string.only_vat_text , finalValue)
        binding.netPrice.text = getString(R.string.net_price, netPriceFinal)
    }


}