package com.hangman

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GamePlayController {
   static allowedMethods = [guess: "PUT"]
   //static responseFormats = ['html', 'json', 'xml']

   def gameLogic

   // Show available games for user
   // http://localhost:8080/hangman/gamePlay/index?userId=1
    def index() {
        println "index() ${params}"
        def games = User.get(params?.userId)?.games
        respond games, [model: [gameInstanceList: games]]
    }


    // Show the game
    // http://localhost:8080/hangman/gamePlay/show/1
    def show(Game game) {
    	println "index() ${params}"
    	respond game
	}


    @Transactional
    def guess(Game gameInstance) {
        println "update()"
        if (gameInstance == null) {
            notFound()
            return
        }

        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'edit'
            return
        }

        gameInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Game.label', default: 'Game'), gameInstance.id])
                redirect show
            }
            '*'{ respond gameInstance, [status: OK] }
        }
    }    
}
