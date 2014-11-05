package com.hangman

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GamePlayController {
   //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
   //static responseFormats = ['html', 'json', 'xml']

    def index() {
        //render (view: "index", model: [Game.get(gameId)])
        println "index() ${params}"
        def game = Game.get(params?.id)
        respond game
    }

}
