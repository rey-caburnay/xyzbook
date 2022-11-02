# xyzbook
spring boot  2.7.5, reactjs v18.2.0 , H2 database

[Features]:
- get all books ( Get '/books') 
- add book ( Post '/book' )
- delete Book (Delete '/book')
- convert and validate isbn13 to isbn10 and vice versa ( Get '/book/{isbn}/validate' )

- get all authors (Get '/authors')
- get all publishers (Get '/publishers')
- update book cover (Put '/book/{isbn}/cover')
- view book (Get '/book/{isbn}')




[director structure]
- xyzbook
  -src


[Requirements]
- gradle



 
How to Run:

Given that this repository is already clone or downloaded.

Backend Server side

1. open terminal or command prompt.
2. cd to xyzbook
3. execute ./gradlew bootRun


