// For vendors for example jQuery, Lodash, angular2-jwt just import them here unless you plan on
// chunking vendors files for async loading. You would need to import the async loaded vendors
// at the entry point of the async loaded file. Also see custom-typings.d.ts as you also need to
// run `typings install x` where `x` is your module

// Angular 2
import 'angular2/bundles/angular2-polyfills';
import 'angular2/platform/browser';
import 'angular2/core';
import 'angular2/http';
import 'angular2/router';

/*

if ('production' === ENV) {
    // Production


} else {
    // Development

}*/
