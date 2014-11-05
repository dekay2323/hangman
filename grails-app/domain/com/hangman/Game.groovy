package com.hangman

class Game {
	String user
	String solution

    static constraints = {
    	solution nullable: false, blank: false
    }
}
