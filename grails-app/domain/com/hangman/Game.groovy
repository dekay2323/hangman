package com.hangman

class Game {
	User user 
	Solution solution
	
	static hasMany = [turns: Turn]

    static constraints = {
    }
}
