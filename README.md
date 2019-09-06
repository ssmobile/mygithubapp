# Git Hub app

This application is designed to make requests to the GitHub API and display the data retrieved onto the screen properly.

## User Activity

<!--![](https://github.com/ssmobile/mygithubapp/blob/master/screenshots/UserActivity.png?raw=true) -->

<img src="https://github.com/ssmobile/mygithubapp/blob/master/screenshots/UserActivity.png?raw=true" width="100">

The first activity contains an ImageView for the user profile image and some Textviews displaying user information. 

An InputStreamReader is instantiated opening a stream to the GitHub API making a user request.

After all the data has been streamed into a String using a BufferedReader, the response is stored in a callback.

The callback is implemented in the MainActivity, where the JSON String is passed into a GSON object and passed into the User POJO that has been created to reflect the JSON response.

Picasso is used to display the image of the GitHub user onto the ImageView.

When the user presses on the button with the number of repositories the GitHub user has, they are redirected to the repository activity.

## Repository Activity

![](https://github.com/ssmobile/mygithubapp/blob/master/screenshots/RepoActivity.png?raw=true)

In this activity a RecylerView populates items containing information about the GitHub user's repositories.

The user can see the Name, language and last update of the repository. 

Instead of using a BufferedReader, since the String from the response is too large, we are using a JSON Reader that will split the data into a JSON array. Each element of the JSON array is passed into a GSON object, which passes the data into a POJO created to store repository data. The list of Repositories is then past into the callback.

The Repository activity accepts the callback and passes the JSON response into the RecyclerView adapter.
 
A function is run to figure out the time differnce between now and the last update, to display how long ago the last update occured, rather than the time it did.

Additionally, the Repository POJO is implementing Comparable, so that the list of Repositories is sorted by last update and displayed accordingly.
