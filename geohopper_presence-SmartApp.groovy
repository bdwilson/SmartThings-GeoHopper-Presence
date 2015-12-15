/**
 *  Geohopper API Presence App
 *
 *  Copyright 2015 Brian Wilson
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Geohopper API Presence App",
    namespace: "bw",
    author: "Brian Wilson",
    description: "Geohopper API Presence App",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    oauth: true)


preferences {
  section ("Allow external service to control these things...") {
    input "presence", "capability.presenceSensor", multiple: true, required: true
  }
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
}

def listLocations() {
    def resp = []
    presence.each {
      log.debug "RECEIVED: ${it.displayName}, attribute ${it.name}, ID: ${it.id}"
      resp << [Name: it.displayName, ID: it.id]
    }
    return resp
}

def deviceHandler(evt) {}

void updateLocation() {
    update(presence)
}
private void update(devices) {
    log.debug "update, request: params: ${params}, devices: $devices.id"
    def location = params.location
    def action = params.action
    def device = devices.find { it.displayName == params.location }
    log.debug "update, request: params: ${params}, devices: $devices.id, $devices.displayName"

    if (location){
        if (!device) {
            httpError(404, "Device not found")
        } else {
            if(action == "LocationExit"){
                  device.off();
            } else { 
                  device.on();
            }
        }
    }
}

mappings {
    path("/listLocations") {
        action: [
            GET: "listLocations"
        ]
    }
    path("/location/:location/:action") {
        action: [
            GET: "updateLocation"
        ]
    }
}
// TODO: implement event handlers
