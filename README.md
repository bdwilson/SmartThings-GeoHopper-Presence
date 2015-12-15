GeoHopper Virtual Presence Updater for SmartThings
=======
<br>
GeoHopper is an app for iOS and Android that uses either device GPS or
Bluetooth LE to GeoHopper
[iBeacons](http://store.twocanoes.com/collections/ibeacons). This device
driver, php script and SmartApp will allow you to use Bluetooth on your mobile
device to set presence in SmartThings. This means you can now get accurate
presence without using GPS and without using a presence sensor (which also
requires a SmartThings hub nearby). 

Requirements
------------
To get started you'll need:
- [GeoHopper](https://itunes.apple.com/us/app/geohopper/id605160102?mt=8).
- An iBeacon (or you can use GPS in the GeoHopper; however why wouldn't you just use SmartThings
  app for GPS presence?). 
- A webserver. Required to run a .php script that interfaces between GeoHopper and
  SmartThings (because GeoHopper web scripts cannot send header auth data to
SmartThings).
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
6. Find a webserver where you can run PHP code from.  Edit geohopper.php.  Set
'''authkey''' to be something you can type into your GeoHopper app.  Set
'''access_key''' to be your API Token from step 4 and set '''endpoint_id'''
to be the token at the end of your Endpoint URL from step 5 (not the whole URL,
just the long token at the end). 
7. Install GeoHopper app and your iBeacon.  In the app, go to Settings->Web
Service and configure a new web service:
http://your_web_server/geohopper.php?auth=your_auth_key. Select "Post". You can
select Execute Locally if the server will be on the same network your mobile
device is on when the triggers need to run. 
8. Configure a GeoHopper location or rename your iBeacon to be whatever the
name of your device was in step 2.  For this location, enable your web service
and perform a test.  It should perform the "On" or "Present" action (check your
SmartThings IDE to make sure you see the logs rolling in.  If not, make sure
you have your authcode set on both the geohopper.php and in the GeoHopper app.  
9. You should now have a virtual presence sensor that you can tie to
SmartThings actions. 

Bugs/Contact Info
-----------------
Bug me on Twitter at [@brianwilson](http://twitter.com/brianwilson) or email me [here](http://cronological.com/comment.php?ref=bubba).


