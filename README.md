# Luminus Authentication Example

This repository was born out of my frustration looking for a practical example of Luminus Web App with a PostgreSQL database and a functioning authentication system.

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen


You will need a [PostgreSQL][2] installed on your machine.

[2]: https://github.com/technomancy/leiningen

## Getting Started

Clone this repository

    git clone https://github.com/ferrucc-io/luminus-auth-example
    
Change the port of the database to the port and name of your database:

    cd luminus-auth-example
    nano src/clj/clj_auth/core.clj

## Running

To start the web server of the application, run:

    lein run 

## License

Copyright © 2018 FIXME
