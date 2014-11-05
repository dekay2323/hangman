package com.hangman

import grails.rest.RestfulController
import org.springframework.transaction.annotation.*

class GameController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def index() {
        respond Game.list(params)
    }

    def show(Game game) {
    	respond game
    }

    @Transactional
	def save(Game game) {
		if(game.hasErrors()) {
    		respond game.errors, view:'create' 
		} else {
			game.save flush:true
        	render status: 201
		}
	}
}
