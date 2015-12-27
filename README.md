GeoHopper Virtual Presence Updater for SmartThings
=======
<br>
GeoHopper is an app for iOS and Android that uses either device GPS or
Bluetooth LE to GeoHopper
[iBeacons](http://store.twocanoes.com/collections/ibeacons). This device
driver and SmartApp will allow you to use Bluetooth on your mobile
device to set presence in SmartThings. This means you can now get accurate
presence without using GPS and without using a presence sensor (which also
requires a SmartThings hub nearby). 

Requirements
------------
To get started you'll need:
- [GeoHopper](https://itunes.apple.com/us/app/geohopper/id605160102?mt=8).
- An iBeacon (or you can use GPS in the GeoHopper; however why wouldn't you just use SmartThings
  app for GPS presence?). 
- SmartThings Hub
	- [A Virtual Presence Device](https://github.com/ajpri/STApps/blob/master/devicetypes/ajpri/virtual-mobile-presence.src/virtual-mobile-presence.groovy)
	- A SmartApp assigned to your Virtual Presence Device

Installation
--------------------
1. Go to [My Device Types](https://graph.api.smartthings.com/ide/devices) and
create a new device using [virtual-mobile-presence.groovy](https://github.com/ajpri/STApps/blob/master/devicetypes/ajpri/virtual-mobile-presence.src/virtual-mobile-presence.groovy).
2. Go to [My Devices](https://graph.api.smartthings.com/device/list) and create
a new device '''name it whatever the location name will be called in
GeoHopper'''.  Network Device ID can be '''VIRTUAL_PRESENCE_1'''. Type will be
the type you configured in step 1.  Make sure Locaion and/or Hub is selected
and select create.
3. Go to [My SmartApps](https://graph.api.smartthings.com/ide/app/create) and
create a new SmartApp from code geohopper_presence-SmartApp.groovy. Save it, go
back to App Settings and enable OAuth. Update your app.  
4. Launch the Editor for your new SmartApp, Save, Publish, Set the location,
Select your new device created in step 2 and click install.
5. Grab the API Token and Endpoint URL from the bottom of the simulator window. 
6. Copy the Endpoint URL and add '''/location/?access_token=[API Token From
Above]'''.  This will be your URL to configure in GeoHopper.  It should look
something like this:
https://graph.api.smartthings.com/api/smartapps/installations/2654dfb4-0c85-4025-xxxxx-xxxxx/location/?access_token=9c7bc742-19d6-4cc9-80a6-xxxxxxxxxxx
7. Now paste this URL into your browser and make sure you get the following
response:
<code>
{"error":true,"type":"SmartAppException","message":"Yep, this is the right URL."}
</code>
8. Install GeoHopper app and your iBeacon.  In the app, go to Settings->Web
Service and configure a new web service using the URL above and 
select "Post". You can
select Execute Locally if the server will be on the same network your mobile
device is on when the triggers need to run. 
9. Configure a GeoHopper location or rename your iBeacon to be whatever the
name of your device was in step 2.  For this location, enable your web service
and perform a test.  It should perform the "On" or "Present" action (check your
SmartThings IDE to make sure you see the logs rolling in.  If not, make sure
you have your URL correct in the GeoHopper app. Make sure step 7 completed
correctly.
9. You should now have a virtual presence sensor that you can tie to
SmartThings actions. 

Bugs/Contact Info
-----------------
Bug me on Twitter at [@brianwilson](http://twitter.com/brianwilson) or email me [here](http://cronological.com/comment.php?ref=bubba).


