package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def gameLogicService

    // list all games
    // ttp://localhost:8080/hangman/game/index
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

        // Actual game logic
        gameInstance.score = gameLogicService.calcScore(gameInstance.solution?.toList(), gameInstance.guess?.toList(), gameInstance.score)
        // service.hasLost(score)
        gameInstance.answers = gameLogicService.newAnswers(gameInstance.answers, gameInstance.guess?.toList())
        //service.hasWon(solution, answers) == false
        gameInstance.currentSolution = gameLogicService.printer(gameInstance.solution?.toList(), gameInstance.answers?.toList())      

        if (gameInstance.hasErrors()) {
            respond gameInstance.errors, view:'edit'
            return
        }

        gameInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Game.label', default: 'Game'), gameInstance.id])
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
}
