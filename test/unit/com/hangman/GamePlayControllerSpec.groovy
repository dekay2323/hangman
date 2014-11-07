package com.hangman



import grails.test.mixin.*
import spock.lang.*

@TestFor(GamePlayController)
@Mock([Game, User, GameLogicService])
class GamePlayControllerSpec extends Specification {

    def setup() {
        // Load some test data 
       /* def user1 = new User(name: "Demian")
        user1.save(flush: true)
        def user2 = new User(name: "Carrie")
        user2.save(flush: true)               
        def game = new Game(user: user1, solution: "Tiberius", question: "Captain Kirk's middle name?")
        game.save(flush: true)
        game = new Game(user: user2, solution: "Vulcan", question: "Spock's birth planet?")
        game.save(flush: true)            
        game = new Game(user: user1, solution: "James Doohan", question: "Which Star Trek Actor Is Responsible For Inventing The Klingon Language?")
        game.save(flush: true) */
    }

    def populateValidParams(params) {
        assert params != null
        params.solution = "Vulcan"
        params.question = "Spock's birth planet?"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.gameInstanceList
            model.gameInstanceCount == 0
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def game = new Game(params)
            controller.show(game)

        then:"A model is populated containing the domain instance"
            model.gameInstance == game
    }


    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)
            controller.gameLogicService = new GameLogicService()

        then:"A 404 error is returned"
            response.redirectedUrl == '/gamePlay/index'
            flash.message != null

        // @TODO: Negative case should work
        /*when:"An invalid domain instance is passed to the update action"
            response.reset()
            def game = new Game()
            game.validate()
            controller.update(game)

        then:"The edit view is rendered again with the invalid instance"
            view == 'show'
            model.gameInstance == game*/

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            def game = new Game(params).save(flush: true)
            controller.update(game)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/gamePlay/show/$game.id"
            flash.message != null
    }


}
