package com.hangman

import grails.rest.RestfulController

class GameController extends RestfulController {
    static responseFormats = ['json', 'xml']

    GameController() {
        super(Game)
    }
}
