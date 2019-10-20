var webpack = require('webpack');
var path = require('path');

var OUTPUT_FOLDER = 'dist';

module.exports = {
    entry: {
        'app': './src/app/main.ts',
        'vendor': './src/app/vendor.ts'
    },
    output: {
        path: path.join(__dirname, OUTPUT_FOLDER),
        filename: "bundle.js"
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin('vendor', 'vendor.bundle.js')
    ],

    resolve: {
        extensions: ['', '.ts', '.js']
    },

    module: {
        loaders: [
            { test: /\.ts$/, loader: 'ts-loader' },
        ],
        noParse: [ /angular2\/bundles\/.+/ ]
    },

    devServer: {
        historyApiFallback: true
    }
};