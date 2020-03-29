#!/bin/bash

if [ -z "$2" ]
  then
    echo "No argument supplied"
    exit 1
fi

EMAIL=$1
PASSWORD=$2

URL=$(curl -s -X POST 'https://twinoid.com/user/login' --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'&submit=Login&host=www.kadokado.com&ref=&refid=&url=%2F&mode=&proto=http%3A&mid=&fver=100.0.0' | hxnormalize -x | hxselect -s '\n' 'input[name='url']' | sed -n 's/^.*value="\(\S*\)".*$/\1/p')

#echo $URL

SID1=$(curl -s -X POST 'https://twinoid.com/user/login' --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'&submit=Login&host=www.kadokado.com&ref=&refid=&url=%2F&mode=&proto=http%3A&mid=&fver=100.0.0' | hxnormalize -x | hxselect -s '\n' 'input[name='sid']' | sed -n 's/^.*value="\(\S*\)".*$/\1/p')

#echo $SID1

URL_ENCODED=$(urlencode $URL)

#echo $URL_ENCODED

SID2=$(curl -s -c - -L 'https://twinoid.com/user/redir' -H 'Content-Type: application/x-www-form-urlencoded' -H 'Cookie: tw_sid='$SID1'; tp_login=1' --data-urlencode 'login='$EMAIL'' --data 'pass='$PASSWORD'' --data 'url='$URL_ENCODED''  | grep sid | sed -n 's/^.*sid\t\(\S*\).*$/\1/p')

echo $SID2
