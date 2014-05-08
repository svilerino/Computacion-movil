var express = require('express');
var app = express();

app.set('views', __dirname + '/views');
app.engine('html', require('ejs').renderFile);
app.use(express.bodyParser());
APP_LISTENING_PORT = 3412;

/* device's logic */

function Device(email, password) {

	return {
		'email' : email,
		'password' : password,
		'data' : [],
	};
}

var devices = [];

devices.activateDevice = function(email, password) {

	devices.push(Device(email, password));
}

devices.deviceIsActivated = function(email) {

	var device = this.filter(function(e) { return e['email'] == email; });
	return device.length > 0;
}

devices.getDevice = function(email) {

	var device = this.filter(function(e) { return e['email'] == email; });
	return device[0];
}

devices.addDataPoint = function(device, latitude, longitude, tag) {

	device['data'].push({'lat' : latitude, 'long' : longitude, 'tag' : tag});
}

/* handling requests */
/*
	- activate
		post: email and password
	- upload
		post: email, password, latitude, longitude and tag
	- downloadDataPoints
		post: email, password
*/

ResponseCode = {
	SUCCESS 				: 0,
	EMAIL_TAKEN 			: 1,
	BAD_SYNTAX	 			: 2,
	DEVICE_NOT_ACTIVATED	: 3,
	WRONG_PASSWORD			: 4,
}

app.post('/activate', function(req, res) {

	console.log("Activate request.");

	if(!req.body.hasOwnProperty('email') || !req.body.hasOwnProperty('password')) {
		return res.json({'activate_response_code' : ResponseCode.BAD_SYNTAX});
	}

	var email = req.body.email;
	var password = req.body.password;

	console.log("Activate request. " + email + ":" + password);

	if(devices.deviceIsActivated(email)) {
		res.json({'activate_response_code' : ResponseCode.EMAIL_TAKEN});
		return;
	}

	devices.activateDevice(email, password);
	res.json({'activate_response_code' : ResponseCode.SUCCESS});
});

app.post('/upload', function(req, res) {

	if(!req.body.hasOwnProperty('email') || !req.body.hasOwnProperty('password') ||
		!req.body.hasOwnProperty('latitude') || !req.body.hasOwnProperty('longitude') || !req.body.hasOwnProperty('tag')) {
		return res.json({'upload_response_code' : ResponseCode.BAD_SYNTAX});
	}

	var email = req.body.email;
	var password = req.body.password;
	var latitude = req.body.latitude;
	var longitude = req.body.longitude;
	var tag = req.body.tag;

	console.log("Upload request. " + email + ":" + password + " (" + latitude + ", " + longitude + ") at " + tag );

	if(!devices.deviceIsActivated(email)) {
		res.json({'upload_response_code' : ResponseCode.DEVICE_NOT_ACTIVATED});
		return;
	}

	var device = devices.getDevice(email);
	if(device['password'] != password) {
		res.json({'upload_response_code' : ResponseCode.WRONG_PASSWORD});
		return;
	}

	devices.addDataPoint(device, latitude, longitude, tag);
	res.json({'upload_response_code' : ResponseCode.SUCCESS});
});

app.post('/download', function(req, res) {

	if(!req.body.hasOwnProperty('email') || !req.body.hasOwnProperty('password')) {
		return res.json({'download_response_code' : ResponseCode.BAD_SYNTAX, 'data' : []});
	}

	var email = req.body.email;
	var password = req.body.password;

	console.log("Download request. " + email + ":" + password);

	if(!devices.deviceIsActivated(email)) {
		res.json({'download_response_code' : ResponseCode.DEVICE_NOT_ACTIVATED, 'data' : []});
		return;
	}

	var device = devices.getDevice(email);
	if(device['password'] != password) {
		res.json({'download_response_code' : ResponseCode.WRONG_PASSWORD, 'data' : []});
		return;
	}

	res.json({'download_response_code' : ResponseCode.SUCCESS, 'data' : device['data']});
});

/* for debugging */

app.get('/list', function(req, res) {

	res.json(devices);

});

/* for debugging */


/* begin listening */
app.listen(process.env.PORT || APP_LISTENING_PORT);
