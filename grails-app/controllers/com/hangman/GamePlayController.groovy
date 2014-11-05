package com.hangman

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GamePlayController {
   //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
   //static responseFormats = ['html', 'json', 'xml']

   // Show available games for user
   // http://localhost:8080/hangman/gamePlay/index?userId=1
    def index() {
        println "index() ${params}"
        def games = User.get(params?.userId)?.games
        respond games, [model: [gameInstanceList: games]]
    }


    // Show a actual game
    // http://localhost:8080/hangman/gamePlay/show/1
    def show(Game game) {
    	println "index() ${params}"
    	respond game
	}

	// Make a guess
	// http://localhost:8080/hangman/gamePlay/guess/1
    def guess(Game gameInstance) {
        respond gameInstance
    }
}
