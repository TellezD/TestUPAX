package com.daniel.android.testupax.fragments

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.daniel.android.testupax.databinding.EmployeesFragmentBinding
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper.Companion.BIRTH
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper.Companion.EMPLOYEES
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper.Companion.ID
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper.Companion.NAME
import com.daniel.android.testupax.utils.EmployeesSQLiteOpenHelper.Companion.ROL
import com.daniel.android.testupax.utils.model.EmployeeDataModel

class EmployeesFragment : Fragment() {

    private val binding by lazy {
        EmployeesFragmentBinding.inflate(layoutInflater)
    }

    private val employees by lazy {
        listOf(
            EmployeeDataModel(1, "Miguel Cervantes", "08-Dic-1990", "Desarrollador"),
            EmployeeDataModel(2, "Juan Morales", "03-Jul-1990", "Desarrollador"),
            EmployeeDataModel(3, "Roberto Méndez", "14-Oct-1990", "Desarrollador"),
            EmployeeDataModel(4, "Miguel Cuevas", "08-Dic-1990", "Desarrollador")

        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (shouldSaveData()) {
            setData()
        }
        getData()
    }

    private fun getData() {
        val employees = EmployeesSQLiteOpenHelper(requireContext(), EMPLOYEES, null, 1)
        val bd = employees.writableDatabase
        val query = bd.rawQuery("select $NAME, $BIRTH, $ROL from $EMPLOYEES", null)

        val listItems = mutableListOf<String>()

        for (i in 0 until query.count) {
            query.moveToNext()
            listItems.add("${query.getString(0)}, ${query.getString(1)}, ${query.getString(2)}")
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listItems)
        binding.employeesList.adapter = adapter
    }

    private fun shouldSaveData(): Boolean {
        val pref = requireActivity().getSharedPreferences(EMPLOYEES_PREF, Context.MODE_PRIVATE)
        return pref.getBoolean(DATA_LOADED, false).not()
    }

    private fun updatePreferences() {
        val pref = requireActivity().getSharedPreferences(EMPLOYEES_PREF, Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putBoolean(DATA_LOADED, true)
        edit.apply()
    }

    private fun setData() {
        employees.forEach { employee ->
            val employees = EmployeesSQLiteOpenHelper(requireContext(), EMPLOYEES, null, 1)
            val bd = employees.writableDatabase
            val data = ContentValues()
            data.put(ID, employee.id)
            data.put(NAME, employee.name)
            data.put(BIRTH, employee.date)
            data.put(ROL, employee.rol)
            bd.insert(EMPLOYEES, null, data)
            bd.close()
        }
        updatePreferences()
    }

    companion object {
        const val EMPLOYEES_PREF = "EMPLOYEES_PREF"
        const val DATA_LOADED = "DATA_LOADED"
    }
}