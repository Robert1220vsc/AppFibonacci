package com.robert.fibonacci

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class SecondActivity2 : AppCompatActivity() {
    //se declaran las variables a usar para referenciar a los elementos graficos
    lateinit var resultTextview : TextView
    lateinit var btnCalcular : Button
    lateinit var NumberEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Se inicializa cada elemento grafico por el id, y se asignan a las variables creadas
        resultTextview = findViewById<TextView>(R.id.resultTextView)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        NumberEditText = findViewById<EditText>(R.id.NumberEditText)

        //Se crea un evento al boton que cuando se haga click sobre el, tome el numero del campo
        //valide que haya datos, si lo hay entonces convierte ese numero a entero y manda a llamar
        // a la funcion que hace el calculo pasandole el numero mediante un parametro.
        // si en el campo de texto esta vacio entonces le muestra "Ingrese un número Válido"
        btnCalcular.setOnClickListener {
            val numeroTexto = NumberEditText.text.toString()
            if(numeroTexto.isNotEmpty()){
                val numero = numeroTexto.toInt()
                val resultado = fibonacci(numero)
                resultTextview.text = resultado
            }else{
                resultTextview.text = "Ingrese un número Válido"
            }
        }
        //Se habilita el desplazamiento vertical en resultTextview para manejar resultados largos.
        resultTextview.setMovementMethod(ScrollingMovementMethod())
    }

    fun fibonacci(numero: Int): String{
        // Lista para almacenar los números de la secuencia de Fibonacci
        val fibonacciList = mutableListOf<Int>()
        // Variables iniciales para los primeros dos números de la secuencia
        var numero1 = 0
        var numero2 = 1
        // Bucle for que se ejecuta desde 1 hasta el número ingresado
        for (i in 1..numero) {
            fibonacciList.add(numero1) // Agrega el número actual a la lista
            var suma = numero1 + numero2 // Calcula el siguiente número de la secuencia como la suma de los dos anteriores
            // Actualiza los valores: numero1 toma el valor de numero2, y numero2 toma el valor de suma
            numero1 = numero2
            numero2 = suma
        }
        // Convierte la lista en una cadena de texto separada por comas y la retorna
        return fibonacciList.joinToString(", ")
    }
}