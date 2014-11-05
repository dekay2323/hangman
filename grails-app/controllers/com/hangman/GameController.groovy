package com.hangman

import grails.rest.RestfulController

class GameController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def index(Integer id) {
        respond Game.list(params), model:[gameCount: Game.count()]
    }

    def show(Game game) {
    	respond game
    }
}
