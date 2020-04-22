package com.example.myappointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myappointments.model.Appointment
import kotlinx.android.synthetic.main.activity_appointments.*
import java.util.ArrayList

class AppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(1,"Dr. Gutiérrez Arias", "12/12/2021", "14:00 PM")
        )
        appointments.add(
            Appointment(2,"Dr. Gomez", "13/12/2021", "15:00 PM")
        )
        appointments.add(
            Appointment(3,"Dr. Otro", "14/12/2021", "16:00 PM")
        )

        rvAppointments.layoutManager = LinearLayoutManager(this) //GridLayoutManager
        rvAppointments.adapter = AppointmentAdapter(appointments)

    }
}
