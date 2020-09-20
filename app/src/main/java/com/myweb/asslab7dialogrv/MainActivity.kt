package com.myweb.asslab7dialogrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*

class MainActivity : AppCompatActivity() {

    val employeeList = arrayListOf<Employee>()

    lateinit var textView : TextView
    lateinit var radioGroup: RadioGroup
    lateinit var radio_button_click: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tv_gender)
        radioGroup = findViewById(R.id.radioGroup)
        radio_button_click = findViewById(R.id.male)
        radio_button_click = findViewById(R.id.female)

        radio_button_click.setOnClickListener{
            var value = radioGroup.checkedRadioButtonId
            var radio_button_click = findViewById<RadioButton>(value)
        }

        employeeData()
        recycler_view.adapter = EmployeeAdapter(this.employeeList, applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }

    fun employeeData() {
        employeeList.add(Employee("Danny", "Male", "danny@kku.ac.th", 30000))
        employeeList.add(Employee("Sara", "Female", "sara@kku.ac.th", 34000))

    }

    fun addEmployee(view: View) {
        val mDialog = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val myBuilder =AlertDialog.Builder(this)
        myBuilder.setView(mDialog)

        val mAlertDialog = myBuilder.show()
        mAlertDialog.btnAdd.setOnClickListener() {
            employeeList.add(
                Employee(
                    mAlertDialog.edt_name.text.toString(),
                    mAlertDialog.radioGroup.checkedRadioButtonId.toString(),
                    mAlertDialog.edt_email.text.toString(),
                    mAlertDialog.edt_salary.text.toString().toInt()
                )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The employee is added successfully",
                Toast.LENGTH_LONG
            ).show()
            mAlertDialog.dismiss()
        }
        mAlertDialog.btnCancel.setOnClickListener() {
            mAlertDialog.dismiss()
        }
    }

}