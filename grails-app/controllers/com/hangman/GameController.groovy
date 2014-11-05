package com.hangman

import grails.rest.RestfulController

class GameController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def index() {
        respond Game.list(params)
    }

    def show(Game game) {
    	respond game
    }
}
