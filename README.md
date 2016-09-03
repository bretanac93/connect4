# Connect 4 REST API
Connect 4 game web services, this API was made using Spring Boot and is intended to be use as Backend of a multiplayer Connect 4 game, the users can register, login and manage their games, by inviting another users, get a list of players by country based on your current location, and of course, play the game against another player.

# Project Information
The project was conceibed as a REST API by implementing the required functionalities as *resources*, the main resources of this project are:

* User: Is the user identity in the project, is only for contain user-related information, such:
	* Username
	* Email
	* Password
	* Country

* Game: Is intended to be the main container of the game, it will hold all the information related to:
	* Board
	* First player
	* Second player

* Turn: Is only for temporal information storage, it will hold all the details of every turn, such:
	* Who play
	* Chosen position ```(x,y)```


#Usage
The following section is going to be explained with the *HTTP Client* ```http```, is a library made in python, and it can be installed as follow:

```sh
pip install http
```
or

```sh
sudo pip install http # in case you need super user rights
```
But you can use ```curl``` or if you prefer a GUI you can use ```Postman``` or ```Paw```, this last one only for OSX users.
After installing the ```http``` client, let's begin the game!.

Sign up and Sign in users
--
In this section, I am going to explain the flow of how to register and authenticate an user, Note that this process is completely stateless by using [JWT](https://jwt.io).

--
**Register an user**
The process to register a user is pretty easy, is just by making a *POST* request to the server and passing all the information related to the user, an example of this:

```sh
http http://localhost:8080/auth/register username=thebest email=thebest@gmail.com password=123 country=DE
```
If everything goes well, the response will be a JSON object containing a message like this one:

```json
{
    "message": "Registered successfully"
}
```
--
**Login**
The Login process is just like the registration, just make a *POST* to the server and send to ``/auth/login`` the information related to the User, the most common way to do this is by *username* and *password*. If everything works fine, then the response will be a JSON object containing the JSON Web Token, an example of how to achieve this:

```sh
http http://localhost:8080/auth/login username=thebest password=123
```

And the response:

```json
{
	"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aGViZXN0IiwiY3JlYXRlZCI6MTQ3MjkzMjA5NjUwMSwiZXhwIjoxNDczNTM2ODk2fQ.Ka0jBGtgJcOJmHUoj8Z1dW2whVae7BmOgs0IGcmwcxb4lumHbhXdMmVMdGOFXjwmanAB37PLAZyYicFXAQkyhg"
}
```
From now, this very same token will be used to represent all the following commands, and to save some space and keep things clear, will be represented with the keyword ``<token>``.

**Note**: From now, if you don't use this token as the value of the Authorization header, you'll receive a 401 response when you ask for a resource to the API, the usage is pretty easy: ``Authorization:"Bearer <token>``

Creating a new game
--
To create a new game, is mandatory to provide the two players id, and the size of the of the board, the request is a POST to the server host, ours is ```http://localhost:8080```:

```sh 
http http://localhost:8080/games width=6 height=6 Authorization:"Bearer <token>"
```
And the response will be a json object containing the resource previously created, it will contain the board as a json grid with an empty board, the dimensions, a variable indicating if the game is finished and the id of the game:

```json
{
    "board": "[[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0]]",
    "board_height": 6,
    "board_width": 6,
    "finished": false,
    "id": 1
}
```
Join to a game
--
A player can create a game or join to a previous created one, the flow for this is making a POST to the game you want to join on, an example below:

```sh
http http://localhost:8080/games/1 Authorization:"Bearer <token>"
```

If everything goes fine, a response like this one is received:

```json
{
    "message": "Joined to the game successfully, enjoy it!"
}
```

Play a turn
--
For play a turn, just need to provide the regarding information, that is the ``id`` of the game you are playing right now, and the position you select for drop your token, just like this example:


```sh
http http://localhost:8080/games/play game_id=1 token_position=1 Authorization:"Bearer <token>"
```
And this **POST** to the server will return the modified game, despite of this method is for modify or update a resource, I decided to make it with a the **POST** HTTP Verb to increase the compatibility for the browsers, the resource returned when this request is done is just like this one:

```json
{
    "board": "[[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0,0],[0,1,0,0,0,0]]",
    "board_height": 6,
    "board_width": 6,
    "finished": false,
    "id": 1
}
```
If you keep some attention to the response, in the *board*, there is the same matrix, but now there is a number indicating your turn, that number is corresponding to the id of the player who drop the token to the board.

API Reference
--

Note that all api calls will be to the localhost in the port 8080, so the host will be ```http://localhost:8080``` and will be represented as ```<host>```

URI | HTTP Method | Function
------------ | ------------- | -------------
/games | *GET* | Retrieve all the games created
/games | *POST* | Create a new game
/games/play | *POST* | The corresponding user plays a turn
/games/join | *POST* | Join to an existing game.
/auth/register | *POST* | Register a new user.
/auth/login | *POST* | Obtain the token corresponding to the user 'logged'
/auth/refresh | *POST*| Refresh the token
/users | *GET* | Retrieve the list of users registered in the system
/users/{username} | *GET* | Retrieve a user given his username


TODO
--

- [ ] Change password of a user.
- [ ] Add real time support using websockets.

