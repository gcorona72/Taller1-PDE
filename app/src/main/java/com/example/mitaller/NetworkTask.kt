package com.example.mitaller

import android.os.AsyncTask
import androidx.compose.runtime.MutableState

class NetworkTask(
    private val progress: MutableState<Int>,
    private val onComplete: () -> Unit
) : AsyncTask<Void, Int, Void>() {

    override fun onPreExecute() {
        super.onPreExecute()
        progress.value = 0
    }

    override fun doInBackground(vararg params: Void?): Void? {
        for (i in 1..100) {
            Thread.sleep(50) // Simula una operación de red
            publishProgress(i)
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        progress.value = values[0] ?: 0
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        onComplete()
    }
}