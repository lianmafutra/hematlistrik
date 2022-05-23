package com.nextproject.hematlistrik

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.nextproject.hematlistrik.databinding.DialogKwhBinding
import com.nextproject.hematlistrik.model.kwh.Kwh
import com.nextproject.hematlistrik.model.kwh.KwhDB
import com.nextproject.hematlistrik.utils.ConvertRupiah
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


@DelicateCoroutinesApi
class DialogPilihKwh : DialogFragment() {

    private var _binding: DialogKwhBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: KwhDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = DialogKwhBinding.inflate(inflater, container, false)

        val items = listOf(
            "1. 900 VA (Rp 1.352 per kWh)",
            "2. 300 VA (Rp 1.444,70 per kWh)",
            "3. 200 VA (Rp 1.444,70 per kWh)",
            "4. "
        )

        val adapter = activity?.let { ArrayAdapter(it, R.layout.list_item_kwh, items) }
        (binding.dropdownKwh as? AutoCompleteTextView)?.setAdapter(adapter)

        cekRbKwh()

        ConvertRupiah().setCurrency(binding.edtKwh2)
        binding.rbKwhStandar.setOnClickListener {
            cekRbKwh()
            hideKeyboard()
        }
        binding.rbKwhManual.setOnClickListener {
            cekRbKwh()
            showKeyboard()
        }

        binding.btnSimpan.setOnClickListener {
            GlobalScope.launch {
                val data = Kwh(binding.edtNamaKwh.text.toString(), Random().toString())
                db.kwhDao().insert(data)
            }
        }

        db = Room.databaseBuilder(requireContext(), KwhDB::class.java, "kwh-db").build()
        return binding.root

    }

    private fun diplayData() {
        val books: List<Kwh> = db.kwhDao().getAll()
        var displayText = ""
        for (book in books) {
            displayText += "${book.nama} | ${book.jenis}\n"
        }
        binding.textView2.text = displayText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cekRbKwh() {
        if (binding.rbKwhStandar.isChecked) {
            binding.inputKwh1.visibility = View.VISIBLE
            binding.inputKwh2.visibility = View.GONE
        }
        if (binding.rbKwhManual.isChecked) {
            binding.inputKwh1.visibility = View.GONE
            binding.inputKwh2.visibility = View.VISIBLE
            binding.edtKwh2.requestFocus()
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Fragment.showKeyboard() {
        view?.let { activity?.showKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Context.showKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}