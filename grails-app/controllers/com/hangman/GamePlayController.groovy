package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

@Transactional(readOnly = true)
class GamePlayController {

    static allowedMethods = [save: "POST", update: ["PUT","GET"], delete: "DELETE"]

  	def gameLogicService

    // list all games
    // http://localhost:8080/hangman/gamePlay/index
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), model:[gameInstanceCount: Game.count()]
    }

    // Show available games for user
   // http://localhost:8080/hangman/gamePlay/forUser/1
    def forUser() {
        def games = User.get(params?.id)?.games
        respond games, [model: [gameInstanceList: games]]
    }

    // Show the game
    // http://localhost:8080/hangman/gamePlay/show/1
    // http://localhost:8080/hangman/gamePlay/show/1.json
    def show(Game gameInstance) {
        respond gameInstance, formats: ['html', 'json']
    }

    @Transactional
    def update(Game gameInstance) {
        if (gameInstance == null) {
            notFound()
            return
        }

        // Game Logic (using JSON)
        def resp = gameLogicService.gameTurnLogic(gameInstance.solution ?: "", gameInstance.answers ?: "", gameInstance.guess ?: "", gameInstance.score)

        flash.message = resp?.gamePlay?.message

        // Update domain object
        gameInstance.properties = resp?.gamePlay

        // Try and save the Game ORM
        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'show'
            return
        }

        gameInstance.save flush:true



        request.withFormat {
            form multipartForm {
                redirect action:"show", id: gameInstance?.id, formats: ['html', 'json']
            }
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

    // http://localhost:8080/hangman/gamePlay/gameTurnLogicSolution?solution=Aba&answers=&guess=a&score=3
    def gameTurnLogicSolution() {
        log.debug "params ${params}"

        def resp = gameLogicService.gameTurnLogic(params?.solution, params?.answers, params?.guess, params?.score?.toInteger())
        render resp as JSON
    }

    // http://localhost:8080/hangman/gamePlay/gameTurnLogic/2?guess=a
    def gameTurnLogic(Game gameInstance) {
       if (gameInstance == null) {
            notFound()
            return
        }

        // Game Logic (using JSON)
        def resp = gameLogicService.gameTurnLogic(gameInstance.solution, gameInstance.answers ?: "", params.guess ?: "", gameInstance.score)

        log.debug resp?.gamePlay
        flash.message = resp?.gamePlay?.message

        // Update domain object
        gameInstance.properties = resp?.gamePlay

        // Try and save the Game ORM
        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'show'
            return
        }

        gameInstance.save flush:true

        // Place domain object into response
        resp?.game = gameInstance

        render resp as JSON
    }
}
