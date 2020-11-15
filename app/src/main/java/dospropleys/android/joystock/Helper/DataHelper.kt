package dospropleys.android.joystock.Helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DataHelper {

    companion object {

        fun getDataAtual(): String {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val currentDateTime = LocalDateTime.now()
                return currentDateTime.format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
            }
            return ""
        }
    }
}