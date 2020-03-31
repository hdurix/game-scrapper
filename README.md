# KakoKado

## Run the app

### With command line

    ./mvnw spring-boot:run
    
### With your IDE

Run main in `KadokadoScrapperApplication.java`.

## Login with shell

### Run the command

    ./sid.sh email@hotmail.fr MyPassword

You will have to run it in Linux distribution with some dependencies:
- curl
- html-xml-utils
- gridsite-clients
- ca-certificates (not sure about this one)

### Example with the app

    SID=$(./sid.sh email@hotmail.fr MyPassword) ./mvnw spring-boot:run

## Login with Docker (recommended on Windows!)

### Build and run the Docker image

    docker build . -t kadokado
    docker run kadokado:latest email@hotmail.fr MyPassword

### Example with the app

    SID=$(docker run kadokado:latest email@hotmail.fr MyPassword) ./mvnw spring-boot:run

## POC (to undestand steps)

To use this POC, you need to replace every string with pattern `!string!`. Here a POC made with:
- [curl](https://curl.haxx.se/)
- Linux command line

### Login

#### Login twinoid

##### Curl example

    EMAIL=!my_mail@hotmail.fr!
    PASSWORD=!MyPass!
    curl -X POST 'https://twinoid.com/user/login' --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'&submit=Login&host=www.kadokado.com&ref=&refid=&url=%2F&mode=&proto=http%3A&mid=&fver=100.0.0'

##### Extract HTML

- url (`<input type="hidden" name="url" value="!URL!`)
- sid first version (`<input type="hidden" name="sid" value="!SID1!"...`)

##### Encode URL

You need to encode `!URL!` to `!URL_ENCODED!`.

For instance with https://www.urlencoder.org/ (copy paste `!URL!` then click on `  > ENCODE <  ` and you'll obtain `!URL_ENCODED!`.

#### Login KadoKado

##### Curl example

    EMAIL=!my_mail@hotmail.fr!
    PASSWORD=!MyPass!
    SID1=!SID1!
    URL_ENCODED=!URL_ENCODED!
    curl -c - -L 'https://twinoid.com/user/redir' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Cookie: tw_sid='$SID1'; tp_login=1' --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'' --data 'url='$URL_ENCODED''

##### Extract cookie

- sid second version (`www.kadokado.com	FALSE	/	FALSE	0	sid	!SID2!`)

### Get Data

#### Curl example

    SID2=!SID2!
    curl http://www.kadokado.com/user/40813 -L --cookie 'sid='$SID2'' | grep Crepuscud
