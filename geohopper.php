<?php

# You make this up and put it in your GeoHopper URL (/geohopper.php?auth=abc1234);
# so not just anyone can use your app
$authkey="1234";
# From your SmartApp OAuth data
$access_key = "9c7bc742-19d6-4cc9-80a6-xxxxxxxxx";
# From your SmartApp OAuth data
$endpoint_id="2654dfb4-0c85-4025-ad7d-xxxxxxxxx";

if ($_REQUEST['auth'] != $authkey) {
	exit;
}

$data = json_decode(file_get_contents('php://input'));

if (!$data->location && !$data->event) {
	exit;
}

# GeoHopper location name must match your Virtual Device Name!!!
$switchUrl="https://graph.api.smartthings.com/api/smartapps/installations/$endpoint_id/location/" . $data->location . "/" . $data->event . "";
$ch = curl_init($switchUrl);
curl_setopt( $ch, CURLOPT_HTTPHEADER, array( 'Authorization: Bearer ' . $access_key ) );
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1 );
curl_setopt($ch, CURLOPT_POST,           0 );
$resp =  curl_exec($ch);
curl_close($ch);
$respData = json_decode($resp,true);
print_r($respData);
?>
