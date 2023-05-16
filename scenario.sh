#!/bin/bash

PACKAGE="tpl.crashreporting.template"
ACTIVITY="tpl.crashreporting.template.MainActivity"

# Launch app
adb shell am start -n $PACKAGE/$ACTIVITY

# Wait few seconds to simulate user waiting
sleep 3


# Press button to crash the app (https://stackoverflow.com/a/50027374/11243782)

## Dump layout file
adb pull $(adb shell uiautomator dump | grep -oP '[^ ]+.xml') view.xml

## Retrieve button coordinates
coords=$(perl -ne 'printf "%d %d\n", ($1+$3)/2, ($2+$4)/2 if /text="CLICK ME!"[^>]*bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"/' view.xml)

## Click button
adb shell input tap $coords


# Sleep some time (expecting that information is sent to the server)
sleep 3

# Close Android message about the crash by clicking outside it
adb shell input tap 500 500

# Wait few seconds to wait until report is sent
sleep 12
