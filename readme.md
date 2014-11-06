#Hangman implementation in Grails#

##Development Steps##
- New Computer
- Install Grails
- Install Java
- Create App
- Push to Github
- Branch AddUser
  - Install Security Plugin, which gives User, Role, Secured
  - Abandon, feels a bit heavy at this point with configuration not goal of assignment
 - Main idea
   - /hangman/game/1
   - restful interface into game
   - front end can be html or other
   - game should be generic enough to handle turn based game
   - Basical Diagram
- Build Domain Classes
	- Use scaffolding to see them hang together
	- Get the basic domain classes correct
- Fixed H2 bug in logging!
- Going to rather spike a solution, REPL
	- Decent results with regualr expressions, but collections with interesctions and stuffs probably better
- Lets retry with more functional ideas
	- Definitely getting somewhere
- Abstract factory? Seems quite felxible in this case
	- Service or just groovy
- Sleeping on it: I think I am trying to hard to solve business problems we do not have.
	- System does not need to support many games
	- Build a good robust system
	- Found some nice collection ways of resolving solution problems
	- Still try keep state out of it.
	- State probably HAS to be in due to picking up saved games (could put solution encrypted into parameter though to avoid state)
	- User could just bookmark a game then (still can do this saving answer)
	- Saving to database would give nice history of games played etc
	- Grails has changed quite a bit
		- Playing with new rest controller
		- New spock testing
	- HTML an JSON version
	- Test driven solution
		- Get the logic working in a serivice
		- Can expose via json rest later
		- Least side effects
	- Build gameplay controller to play game


	DAY 2
	- Uppercase/lowercase should not matter
	- New Grails doc, Groovydoc api, should be using
	** Internal debate of typeing method parameters.
		- Not needed, allows flexibility, whatever is passed in just needs to implement intersect or whatever function is called upon it.
		- More difficult for initial people to pick up, less compiler checking
		- Decided to type them
		- Power assert method exists
	** Add robustness with upfront power asserts, self documenting code is nice!
	- Should add is guess correct
	- Refactor tests to be more modular

	- Robustify tests
		- Test for bizarre stuff
		- upper lower
		- nulls



	#TODOLIST#
	- 9.1.4 Versioning REST resources
	- Griffon
	- Code Coverage
	- Circuit breaker
	- Load Test
	- Profile
	- Async
	