***Social Media Platform API (Twitter Clone)***
This project is a simple social media platform API inspired by Twitter. It includes endpoints for user authentication, posting, and commenting.

API Endpoints

**User Authentication**
Login
  URL: /login
  Method: POST
  Request Body:
      email <str>
      password <str>
  Responses:
      Login Successful
      Username/Password Incorrect
      User does not exist
*Signup*
  URL: /signup
  Method: POST
  Request Body:
    email <str>
    name <str>
    password <str>
  Responses:
    Account Creation Successful
    Forbidden, Account already exists
    User Details
*User Detail*
  URL: /user
  Method: GET
  Query Parameter: userID
  Response Body:
    name <str>
    userID <int>
    email <str>
  Error Response: User does not exist
**Posts**
*User Feed*
  URL: /
    Method: GET
  Response Body:
    posts <object>
    postID <int>
    postBody <string>
    date <date>
    comments <object>
    commentID <int>
    commentBody <string>
    commentCreator <object>
    userID <int>
    name <str>
*Create a Post*
  URL: /post
    Method: POST
    Request Body:
    postBody <string>
    userID <int>
  Responses:
    Post created successfully
    User does not exist
*Retrieve a Post*
  Method: GET
  Query Parameter: postID
  Response Body:
  postID <int>
  postBody <string>
  date <date>
  comments <object>
  commentID <int>
  commentBody <string>
  commentCreator <object>
  userID <int>
  name <str>
  Error Response: Post does not exist
*Edit a Post*
    Method: PATCH
    Request Body:
    postBody <str>
    postID <int>
  Response:
    Post edited successfully
    Post does not exist
*Delete a Post*
Method: DELETE
  Query Parameter: postID <int>
  Responses:
    Post deleted
    Post does not exist
Comments
*Create a Comment*
  URL: /comment
    Method: POST
  Request Body:
    commentBody <string>
    postID <int>
    userID <int>
  Responses:
    Comment created successfully
    User does not exist
    Post does not exist
*Retrieve a Comment*
  Method: GET
  Request Parameter: commentID <int>
  Response Body:
    commentID <int>
    commentBody <string>
    commentCreator <object>
    userID <int>
    name <str>
  Error Response: Comment does not exist
*Edit a Comment*
  Method: PATCH
    Request Body:
      commentBody <str>
      commentID <int>
    Response:
      Comment edited successfully
      Comment does not exist
*Delete a Comment*
  Method: DELETE
    Query Parameter: commentID <int>
  Response:
    Comment deleted
    Comment does not exist
**All Users**
*List All Users*
    URL: /users
    Method: GET
  Response:
    name <str>
    userID <int>
    email <str>
    posts <object>
