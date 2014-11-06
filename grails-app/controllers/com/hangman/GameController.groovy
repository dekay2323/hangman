package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

@Transactional(readOnly = true)
class GameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  	def gameLogicService

    // list all games
    // http://localhost:8080/hangman/game/index
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), model:[gameInstanceCount: Game.count()]
    }

    // Show available games for user
   // http://localhost:8080/hangman/game/listgame?userId=1
    def forUser() {
        println "index() ${params}"
        def games = User.get(params?.id)?.games
        respond games, [model: [gameInstanceList: games]]
    }

    // Show the game
    // http://localhost:8080/hangman/game/show/1
    def show(Game gameInstance) {
        respond gameInstance
    }

    def create() {
        respond new Game(params)
    }

    @Transactional
    def save(Game gameInstance) {
        if (gameInstance == null) {
            notFound()
            return
        }

        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'create'
            return
        }

        gameInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'game.label', default: 'Game'), gameInstance.id])
                redirect gameInstance
            }
            '*' { respond gameInstance, [status: CREATED] }
        }
    }

    def edit(Game gameInstance) {
        respond gameInstance
    }

    @Transactional
    def update(Game gameInstance) {
        println "update() ${gameInstance}"
        if (gameInstance == null) {
            notFound()
            return
        }

        // Game Logic (using JSON)
        def response = gameLogicService.gameTurnLogicJson(gameInstance.solution, gameInstance.answers, gameInstance.guess, gameInstance.score)

        // Change domain object
        gameInstance.answers = response?.gamePlay?.answers
        gameInstance.score = response?.gamePlay?.score
        gameInstance.currentSolution = response?.gamePlay?.currentSolution
        if (response?.gamePlay?.dateWon)
            gameInstance.dateWon = response?.gamePlay?.dateWon
        if (response?.gamePlay?.dateLost)
            gameInstance.dateWon = response?.gamePlay?.dateLost

        flash.message = response?.gamePlay?.message

        // Try and save the Game ORM
        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'edit'
            return
        }

        gameInstance.save flush:true

        request.withFormat {
            form multipartForm {
                redirect gameInstance
            }
            '*'{ respond gameInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Game gameInstance) {

        if (gameInstance == null) {
            notFound()
            return
        }

        gameInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Game.label', default: 'Game'), gameInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
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

    // http://localhost:8080/hangman/game/gameLogic?solution=Aba&answers=&guess=a&score=3
    def gameLogic() {
        println "params ${params}"

        def resp = gameLogicJson(params?.solution, params?.answers, params?.guess, params?.score?.toInteger())
        render resp as JSON
    }




  /*  private void gameLogic(def gameInstance, def flash) {
        assert gameInstance != null, "GameInstance should not be null"
        // Actual game logic
        def solution = gameInstance.solution?.toList()
        def guess = gameInstance.guess

        if (gameLogicService.correctGuess(solution, guess))
            flash.message = "You guessed a letter correctly"
        else
            flash.message = "Wrong guess"

        gameInstance.score = gameLogicService.calcScore(solution, guess, gameInstance.score)
        gameInstance.answers = gameLogicService.newAnswers(gameInstance.answers?.toList(), guess)?.join()
        gameInstance.currentSolution = gameLogicService.printer(solution, gameInstance.answers?.toList())  
        if (gameLogicService.hasWon(solution, gameInstance.answers?.toList())) {
            flash.message = "You have won"
            gameInstance.dateWon = new Date()
        }

        if (gameLogicService.hasLost(gameInstance.score)) { 
            flash.message = "You have lost"
            gameInstance.dateLost = new Date()
        }
    }*/
}
