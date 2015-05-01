# diceware

A password generation utility leveraging the [diceware passphrase](http://world.std.com/~reinhold/diceware.html) word bank.

## Usage

Run via `lein`

    # To generate a 10 word password, run
    > lein run 10
    ;; 1st waist serum horny once allyn vq belch spate allyn

Create an uberjar

    > lein uberjar
    > java -jar target/diceware-0.1.0-SNAPSHOT-standalone.jar 20
    
## License

Copyright © 2015 Daniel De Aguiar

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
