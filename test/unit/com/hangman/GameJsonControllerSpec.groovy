package com.hangman



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.test.mixin.*
import spock.lang.*

@TestFor(GameJsonController)
@Mock([Game, User])
class GameJsonControllerSpec extends Specification {
    
    def populateValidParams(params) {
        assert params != null
        def user1 = new User(name: "Andy")
        user1.save(flush: true)
		params.user = user1.id
		params.solution = "Spock"
		params.question = "Who is a Vulcan"
		return params
    }

    def setup() {
            // Load some test data 
            def user1 = new User(name: "Demian")
            user1.save(flush: true)
            def user2 = new User(name: "Carrie")
            user2.save(flush: true)               
            def game = new Game(user: user1, solution: "testest", question: "Whatever")
            game.save(flush: true)
            game = new Game(user: user2, solution: "Tiberius", question: "Whatever")
            game.save(flush: true)   
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The response is correct"
            response.status == OK.value
        	def jsonResponse = JSON.parse(response.text)
        	jsonResponse.size() == 2     
            // The JSON parse is not deep, it only passes id
        	jsonResponse.find {User.get(it.user.id)?.name == "Carrie"} != null            
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            // Make sure the domain class has at least one non-null property
            // or this test will fail.
            def game = new Game()
            request.method = 'POST'
            controller.save(game)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"The save action is executed with a valid instance"
            response.reset()
            game = new Game(populateValidParams(params))

            controller.save(game)

        then:"The response status is CREATED and the instance is returned"
            response.status == CREATED.value
            response.text == (game as JSON).toString()
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
        	request.method = 'PUT'
            controller.update(null)

        then:"The response status is NOT_FOUND"
            response.status == NOT_FOUND.value

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def game = new Game()
            controller.update(game)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"A valid domain instance is passed to the update action"
            response.reset()
            game = new Game(populateValidParams(params)).save(flush: true)
            controller.update(game)

        then:"The response status is OK and the updated instance is returned"
            response.status == OK.value
            response.text == (game as JSON).toString()
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.method = 'DELETE'
            controller.delete(null)

        then:"A NOT_FOUND is returned"
            response.status == NOT_FOUND.value

        when:"A domain instance is created"
            response.reset()
            def game = new Game(populateValidParams(params)).save(flush: true)

        then:"It exists"
            Game.count() == 3

        when:"The domain instance is passed to the delete action"
            controller.delete(game)

        then:"The instance is deleted"
            Game.count() == 2
            response.status == NO_CONTENT.value
    }
}