## Take home challenge


The main purpose of the app should be to show a list of popular repositories. We consider a repository “popular”, when it has at least 100 followers.
- Create a Search Screen, where user can input a keyword to be searched for and choose one of the sort methods: stars|forks| recently updated. UI is up to you, but keep it simple. Search screen should contain a search button, which takes us to a List Screen.
- Create a List Screen which lists the results of the user search (up to 100 results is fine) among “popular” repositories. Each result row for a repository should contain: id, name, language, owner avatar picture and - based on previously chosen criteria on Search screen - a number of stars or forks or last update date
- Create a Details Screen, that will be opened once clicked on a row in a List Screen. Details Screen should contain  id, name, language, description ,  owner avatar, owner login, number of stars, number of  forks and last update date.

You are free to choose the architecture. You can use publicly available libraries for loading picture.
Please demonstrate error handling - e.g. handle a response with no results for given keyword and no internet connection.

Please add meaningful tests.

Github API doc link https://docs.github.com/en/rest/search#search-repositories


### We expect an incomplete solution. Please spend no more than 4 hours in total on the task, we respect your time and we want to understand how you prioritise technical and non-technical requirements.

Good luck!
UMI devs :)