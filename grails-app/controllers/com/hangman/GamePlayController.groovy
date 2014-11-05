package com.hangman

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GamePlayController {
   //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer gameId) {
        respond Game.get(gameId)
    }

}
