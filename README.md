# WebQuiz
Backend WebQuiz app using Spring


## API [/api](loclahost:8889/api)

#### POST /register

Register a new user

Consumes a JSON:
```
{
  "email": "your@email.com", // Must have a valid format (with @ and .)
  "password": "password" // Must have at least five characters
}
```
### GET /quiz

Get a sample quiz

### POST /quiz

Answer the simple quiz

### POST /quizzes

Post your own quiz

Consumes a JSON:
```
{
  "title": "TITLE HERE", // Must not be empty
  "text": "TEXT HERE", // Must not be empty
  "options": ["OPTION1", "OPTION2"], // Must not be null, must have at least 2 options
  "answer": [0,1]
}
```

### GET /quizzes

Get all the added quizzes

### GET /quizzes/{id}

Get quiz by id specified by the path variable

### POST /quizzes/{id}/solve

Solve quiz by id specified by the path variable

Consumes a JSON:
```
{
  "answer": [0,1]
}
```

### DELETE /quizzes/{id}

Delete your quiz by id specified by the path variable
