#!/bin/bash

if [ -z "$2" ]
  then
    echo "No argument supplied"
    exit 1
fi

EMAIL=$1
PASSWORD=$2

HTML=$(curl -s -X POST 'https://twinoid.com/user/login' \
        --data-urlencode 'login='$EMAIL'' \
        --data 'pass='$PASSWORD'&submit=Login&host=www.kadokado.com&mid='
)

URL=$(echo $HTML | hxnormalize -x | hxselect -s '\n' 'input[name='url']' | sed -n 's/^.*value="\(\S*\)".*$/\1/p')

#echo $URL

TW_SID=$(echo $HTML  | hxnormalize -x | hxselect -s '\n' 'input[name='sid']' | sed -n 's/^.*value="\(\S*\)".*$/\1/p')

#echo $TW_SID

SID=$(curl -s -c - -L 'https://twinoid.com/user/redir' \
        --cookie 'tw_sid='$TW_SID'' \
        --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'' --data-urlencode 'url='$URL'' \
        | grep sid | sed -n 's/^.*sid\t\(\S*\).*$/\1/p'
)

echo $SID
