
//Notice: use Material date seletor as the template 

import React, {useEffect, useState}from "react";
import dayjs from 'dayjs';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';


function DueDatePicker({handleDateChange}){

    const [selectedDate, setSelectedDate] = useState(dayjs());

    const handleDateChangeLocal = (date) => {
        setSelectedDate(date);

        // pass the selected date back to CreateTaskForm
        handleDateChange(date); 
    };

    return(
    
        <LocalizationProvider dateAdapter={AdapterDayjs}>
        
            <DatePicker 
            
                label="Set Due Day"
                value={selectedDate}
                onChange={handleDateChangeLocal}
            />
    
        
        </LocalizationProvider>
        
    );
}

export default DueDatePicker;