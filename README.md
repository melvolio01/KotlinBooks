## Kotlin Books

This is a simple app built whilst learning to use Kotlin for Android development. The App allows users to search for books (using Retrofit/OKHttp requests to the GoogleBooks API) and add them to/remove them from their collection of favourites (stored in a local Room Database Entity).

### Running the app

* In order to run the application you will need a GoogleBooks API key (https://developers.google.com/books/docs/v1/using#APIKey).

* Download/Clone a copy of the project (https://github.com/melvolio01/KotlinBooks.git)

* Open the project in Android Studion and create a file called apikey.properties (at the same level as .gitignore and build.gradle) and add your key to it as follows:

    #### GOOGLE_BOOKS_KEY="your_api_key"

It should then be possible to build the app (although Google may require some additional registration steps) and run it on the Android Studio device emulator or on an actual device.  
