package anna.shcherbak.wiekpokazanywminutach

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.MonthDay
import java.util.*

// PROJEKT PRZEZNACZONY WYLACZNIE DO OCENY, BEZ MOŻLIWOŚCI PÓŹNIEJSZEGO UDOSTĘPNIANIA KODU ŹRÓDŁOWEGO
// WYKONANY PRZEZ ANNE SHCHERBAK (NR ALBUMU: 64699)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view) }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR) //aktualny rok w czasie klikniecia
        val month = myCalendar.get(Calendar.MONTH) // aktualny miesiąc w czasie kliknięcia
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH) //aktualny dzien w czasie klikniecia

        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth -> // data (rok, miesiac, dzień) jaką wybraliśmy z klasy DatePickerDialog

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear" // wpisujemy format daty (jako string)

                    tvSelectedDate.setText(selectedDate) //ustawiamy tekst w tvSelectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) // tworzymy patTern daty z klasy SimpleDateFormat

                    val theDate = sdf.parse(selectedDate) //przetwarzamy format stringu w (selected date) na dane typu DATA

                    val selectedDateInMinutes = theDate!!.time / 60000 //( od 1 january 1970 ) przeliczamy obiekt theDate poprzez funkcję time (zwracającą w milisekundach) i dzielimy przez 60 000 żeby uzyskać minuty

                    val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentTimeInMinutes = currentTime!!.time / 60000

                    val timeDiffrence = (currentTimeInMinutes - selectedDateInMinutes).toString()

                    tvSelectedDateInMinutes.setText(timeDiffrence)


                },year,month, dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time - 86400000) // ustawiamy, żeby nasz DatePikerDialog nie wychodził w przyszłość (max. jeden dzień w tył do wyboru)
        dpd.show()
    }
}