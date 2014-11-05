package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GameJsonController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Game.list(params), [status: OK]
    }

    @Transactional
    def save(Game gameInstance) {
        if (gameInstance == null) {
            render status: NOT_FOUND
            return
        }

        gameInstance.validate()
        if (gameInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        gameInstance.save flush:true
        respond gameInstance, [status: CREATED]
    }

    @Transactional
    def update(Game gameInstance) {
        if (gameInstance == null) {
            render status: NOT_FOUND
            return
        }

        gameInstance.validate()
        if (gameInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        gameInstance.save flush:true
        respond gameInstance, [status: OK]
    }

    @Transactional
    def delete(Game gameInstance) {

        if (gameInstance == null) {
            render status: NOT_FOUND
            return
        }

        gameInstance.delete flush:true
        render status: NO_CONTENT
    }
}
