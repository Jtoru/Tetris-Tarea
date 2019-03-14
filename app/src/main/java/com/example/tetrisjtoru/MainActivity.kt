package com.example.tetrisjtoru

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.tetrisjtoru.model.Game
import com.example.tetrisjtoru.view.Tetris

class MainActivity : AppCompatActivity() {
    lateinit var restartBtn : Button
    var score : TextView? = null
    lateinit var tetris : Tetris
    private val model = Game()

    //https://books.google.co.cr/books?id=OrZTDwAAQBAJ&pg=PA137&lpg=PA137&dq=tetris+android+kotlin&source=bl&ots=zPI0VJogd_&sig=ACfU3U0Ml6XLvIa_cqdiCDFz_XwryXntuA&hl=es-419&sa=X&ved=2ahUKEwjY8PaVxf_gAhWIzlkKHfCABZ4Q6AEwDHoECAIQAQ#v=onepage&q=tetris%20android%20kotlin&f=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restartBtn = findViewById(R.id.btn_restart)
        score = findViewById(R.id.tv_current_score)
        tetris = findViewById(R.id.view_tetris)
        tetris.setActivity(this)
        tetris.setModel(model)

        restartBtn.setOnClickListener {
            model.restartGame()
        }

        updateScore()


        tetris.setOnTouchListener { view, event -> onTetrisViewTouch(view, event) }
    }
    private fun onTetrisViewTouch(view: View, event: MotionEvent):
            Boolean {
        if (model.isGameOver() || model.isGameAwaitingStart()) {
            model.startGame()
            tetris.setGameCommandWithDelay(Game.Motions.DOWN)
        } else if(model.isGameActive()) {
            when (resolveTouchDirection(view, event)) {
                0 -> moveElement(Game.Motions.LEFT)
                1 -> moveElement(Game.Motions.ROTATE)
                2 -> moveElement(Game.Motions.DOWN)
                3 -> moveElement(Game.Motions.RIGHT)
            }
        }
        return	true
    }

    private fun resolveTouchDirection(view: View, event: MotionEvent):
            Int	{
        val x = event.x / view.width
        val	y = event.y	/ view.height
        val	direction: Int
        direction =	if (y > x) {
            if (x > 1 - y) 2 else 0
        }
        else {
            if (x >	1 -	y) 3 else 1
        }
        return	direction
    }

    private fun moveElement(motion: Game.Motions) {
        if (model.isGameActive()) {
            tetris.setGameCommand(motion)
        }
    }

    private fun updateScore(){
        score?.text = "0"
    }




}
