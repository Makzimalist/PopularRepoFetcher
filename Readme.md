This is a basic android native app for searching and fetching GitHub repositories. This is a case study for an application.

# The challenge

The main purpose of the app should be to show a list of popular repositories. We consider a repository “popular”, when it has at least 100 followers.
- Create a Search Screen, where user can input a keyword to be searched for and choose one of the sort methods: stars|forks| recently updated. UI is up to you, but keep it simple. Search screen should contain a search button, which takes us to a List Screen.
- Create a List Screen which lists the results of the user search (up to 100 results is fine) among “popular” repositories. Each result row for a repository should contain: id, name, language, owner avatar picture and - based on previously chosen criteria on Search screen - a number of stars or forks or last update date
- Create a Details Screen, that will be opened once clicked on a row in a List Screen. Details Screen should contain  id, name, language, description ,  owner avatar, owner login, number of stars, number of  forks and last update date.

You are free to choose the architecture. You can use publicly available libraries for loading picture.
Please demonstrate error handling - e.g. handle a response with no results for given keyword and no internet connection.

Please add meaningful tests.

Github API doc link https://docs.github.com/en/rest/search#search-repositories
### We expect an incomplete solution. Please spend no more than 4 hours in total on the task, we respect your time and we want to understand how you prioritise technical and non-technical requirements.


## Problems and missing things
Due to the small time window some parts are not working as expected or are as beautiful as I would love to see them.
what is Missing:
* DetailScreen (wanted to focus more on tests and other parts)
* UI tests
* proper documentation and commenting in the code
* Modularization (tried to display it by packaging)
  * in a perfect world I may would have splitted it into multiple modules, maybe for UI, Model (Common) stuff and api
* gradle dependency cleanup


Notes:
* for any reason my .gitignore decided to not work... so there are may some weired files, please ignore them :D
* Only wrote tests for viewModel and the Repository (4hrs are really fast :D)
* I am not happy with the Ui, since I was in a hurry I had to do it quick and dirty
* The composables are not that well structured nor separated into cleaver files
* I am not 100% sure if the try/catch in the listViewModel is the right approach here


My main goal was to ship at least an MVP which is usable, looks not that bad and has at least 2-3 meaningful testcases.
The detail view and UI adjustments could be added in another ticket ;)