# xyzbook
spring boot  2.7.5, reactjs v18.2.0 , H2 database

[Features]:
- display list of books 
- add book
- delete Book
- convert isbn13 to isbn10 and vice versa
- validate isbn13 and isbn10
- display list of authors
- display list of pulishers
- update book cover/image
- view book

[not working]
- add author
- edit author
- delete author
- add publisher
- edit publisher
- delete publisher


[director structure]
- xyzbook
  - montani-exam
    - client


[Requirements]
- gradle
- nodejs version 18


 
How to Run:

Given that this repository is already clone or downloaded.

Backend Server side

1. open terminal or command prompt.
2. cd to xyzbook/montani-exam
3. execute ./gradlew bootRun


UI (given that npm is already installed)
 

1. open another terminal
2. cd to xyzbook/montani-exam/client/app
3. execute yarn install
4. execute yarn start (this should open a web browser if not execute step 5)
5. open web browser and navigate to http://localhost:3000
