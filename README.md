# Book Listing Client and Server #

## Summary ##

* Java based Command line interface app to search for and print books by title or author
* Java/Spring based RESTful API Server that the command line client will communicate with to search for books
* Java version - 17
* Developer - Max Grossman
* Not containerized
* Port - 8080


##  Architecture ##
* Proper diagram to come

User -> Client -> Server -> GoodreadsAPI

## Download and run ##
* Executable .jar files will be available in Github releases
* Download `book-review-listing-x.x-SNAPSHOT.jar` and `book-review-listing-client-x.x-SNAPSHOT.jar`
* Set up your environment variables (see EnvVars in this doc)
  * .env file will be provided with necessary values
  * Normally this would not happen, but for this demo it will be provided
* Start the server by running `java -jar book-review-listing-x.x-SNAPSHOT.jar`
* On the same machine execute whatever searches you want with the client
  * See how to run Client in this doc


## How to run the client ##
* Client jar can be executed by running `java -jar book-review-listing-client-x.x-SNAPSHOT.jar [OPTIONS]`

* *OPTIONS*
* -s --search [TERMS]:
  * search term(s) - just a string search query
    * REQUIRED
    * This is not really validated at all on the client or server side
    * Normally this should be validated
* -p [NUMBER]:
  * page number - the page number to return
    * OPTIONAL - default 1
    * depends on how many pages there are
* --sort [FIELD]
  * field to sort by
    * OPTIONAL - default title
* -h --host
  * print the hostname of the server
* --help
  * print help message
