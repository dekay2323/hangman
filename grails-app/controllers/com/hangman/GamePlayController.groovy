package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

@Transactional(readOnly = true)
class GamePlayController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  	def gameLogicService

    // list all games
    // http://localhost:8080/hangman/gamePlay/index
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), model:[gameInstanceCount: Game.count()]
    }

    // Show available games for user
   // http://localhost:8080/hangman/gamePlay/listgame?userId=1
    def forUser() {
        println "index() ${params}"
        def games = User.get(params?.id)?.games
        respond games, [model: [gameInstanceList: games]]
    }

    // Show the game
    // http://localhost:8080/hangman/gamePlay/show/1
    def show(Game gameInstance) {
        respond gameInstance
    }

    @Transactional
    def update(Game gameInstance) {
        if (gameInstance == null) {
            notFound()
            return
        }

        // Game Logic (using JSON)
        def response = gameLogicService.gameTurnLogic(gameInstance.solution, gameInstance.answers ?: "", gameInstance.guess, gameInstance.score)

        println response?.gamePlay
        flash.message = response?.gamePlay?.message

        // Update domain object
        gameInstance.properties = response?.gamePlay

        // Try and save the Game ORM
        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'edit'
            return
        }

        gameInstance.save flush:true

        request.withFormat {
            form multipartForm {
                redirect action:"show", id: gameInstance?.id
            }
            '*'{ respond action:"show", id: gameInstance?.id, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'game.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    // http://localhost:8080/hangman/gamePlay/logic?solution=Aba&answers=&guess=a&score=3
    def logic() {
        println "params ${params}"

        def resp = gameLogicService.gameTurnLogic(params?.solution, params?.answers, params?.guess, params?.score?.toInteger())
        render resp as JSON
    }
}
