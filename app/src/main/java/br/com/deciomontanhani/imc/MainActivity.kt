package br.com.deciomontanhani.imc

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import br.com.deciomontanhani.imc.extensions.format
import br.com.deciomontanhani.imc.extensions.hideKeyboard
import br.com.deciomontanhani.imc.extensions.valueDouble
import br.com.deciomontanhani.imc.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso, 1))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura))

        btCalcular.setOnClickListener {
            calcular()
            hideKeyboard()
        }
    }

    private fun calcular() {
        val peso = etPeso.valueDouble()
        val altura = etAltura.valueDouble()

        val imc = peso / (altura * altura)

        when(imc) {
            0.0, 18.5 -> configuraIMC(imc, R.drawable.masc_abaixo, R.string.magreza)
            18.6, 24.9 -> configuraIMC(imc, R.drawable.masc_ideal, R.string.peso_normal)
            25.0, 29.9 -> configuraIMC(imc, R.drawable.masc_sobre, R.string.sobre_Peso)
            30.0, 34.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
            35.0, 39.9 -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_ii)
            else -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_iii)

        }
   }

    private fun configuraIMC(imc: Double, drawableId: Int, stringId: Int) {
        tvIMC.text = getString(R.string.seu_imc, imc.format(2))

        ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this, drawableId)
        )

        tvIMCStatus.text = getString(stringId)
    }
}
