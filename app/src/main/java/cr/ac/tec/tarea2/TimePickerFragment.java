package cr.ac.tec.tarea2;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    private Calendar hourCalendar = null;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        if(hourCalendar == null){
            hourCalendar = Calendar.getInstance();
        }
        int hour = hourCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = hourCalendar.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        hourCalendar.set(1950,1,1,hourOfDay,minute);
        ((ListaEventos)getActivity()).updateEvents();
    }

    public Calendar getCalendar(){
        return hourCalendar;

    }
}