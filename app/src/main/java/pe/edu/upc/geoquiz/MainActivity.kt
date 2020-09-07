package pe.edu.upc.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var questions: ArrayList<Question>
    lateinit var listPosition: ArrayList<Int>
    var position = 0
    
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listPosition = ArrayList()
        loadQuestions()
        setupViews()
    }

    private fun loadQuestions(){
        questions = ArrayList()
        questions.add(Question("¿Es Lima la capital de Chile?", false))
        questions.add(Question("¿Es Budapest la capital de Hungría?", false))
        questions.add(Question("¿Es Buenos Aires la capital de Argentina?", true))
        questions.add(Question("¿Es Rio de Janeiro la capital de Brasil?", false))
        questions.add(Question("¿Es Tokio la capital de China?", false))
        questions.add(Question("¿Es Berlín la capital de Alemania?", true))
        questions.add(Question("¿Es Buenos Aires la capital de Argentina?", true))
        questions.add(Question("¿Es Oslo la capital de Noruega?", true))
        questions.add(Question("¿Es Damasco la capital de Libia?", false))
        questions.add(Question("¿Es Nueva Delhi la capital de India?", true))
    }

    @ExperimentalStdlibApi
    private fun setupViews(){
        showSentence()
        btnYes.setOnClickListener {
           validateAnswer(true)
        }
        btnNo.setOnClickListener {
            validateAnswer(false)
        }
        btnNext.setOnClickListener {
            listPosition.add(position)
            position = Random.nextInt(0, questions.size)
            showSentence()
        }
        btnBack.setOnClickListener {
            if(listPosition.size != 0) {
               position = listPosition[listPosition.size - 1]
                listPosition.removeLast()
                showSentence()
            }
        }
    }

    private fun validateAnswer(option: Boolean){
        if(questions[position].isAnswer == option){
            Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSentence() {
        tvSentence.text = questions[position].sentence
    }
}