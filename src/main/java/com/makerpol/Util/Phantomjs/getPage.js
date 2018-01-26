"use strict";
var page = require('webpage').create(),
    system = require('system'),
    address;

phantom.outputEncoding="UTF-8";

if (system.args.length < 2) {
    console.log('Usage: openurlwithproxy.js <proxyHost> <proxyPort> <URL>');
    phantom.exit(1);
} else {

    address = system.args[1];
    page.open(address, function (status) {
        if (status !== 'success') {
            console.log('FAIL to load the address');
        } else {
            console.log(page.content);
        }
        phantom.exit();
    });
}
