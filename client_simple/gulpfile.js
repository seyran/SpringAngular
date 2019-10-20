var gulp = require('gulp');
var gutil = require('gulp-utils');
var del = require('del');
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var webpackConfig = require('./webpack.config.js');

var OUTPUT_FOLDER = "dist";

//copy html and css files
gulp.task('copy', ['clean'], function() {
    return gulp.src([
            'src/index.html',
            'src/**/*.html',
            'src/**/*.css'
        ])
        .pipe(gulp.dest(OUTPUT_FOLDER));
});

//clean the dist folder
gulp.task('clean', function(cb){
    return del([OUTPUT_FOLDER+'/**/*']);
    cb();
});

// build javascript for deployment using webpack
gulp.task('build', ['clean'], function(callback) {
    //modify webpack config options
    var myConfig = Object.create(webpackConfig);
    myConfig.plugins = myConfig.plugins.concat(
        new webpack.DefinePlugin({
            "process.env": {
                // This has effect on the react lib size
                "NODE_ENV": JSON.stringify("production")
            }
        }),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin()
    );
    // run webpack
    webpack(myConfig, function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build", err);
        gutil.log("[build]", stats.toString({
            colors: true
        }));
        callback();
    });
});

gulp.task('deploy',['clean', 'copy','build']);

// modify some webpack config options
var myDevConfig = Object.create(webpackConfig);
myDevConfig.devtool = "sourcemap";
myDevConfig.debug = true;

// create a single instance of the compiler to allow caching
var devCompiler = webpack(myDevConfig);

gulp.task("build-dev", ['clean'], function(callback) {
    // run webpack
    devCompiler.run(function(err, stats) {
        if(err) throw new gutil.PluginError("build-dev", err);
        gutil.log("[build-dev]", stats.toString({
            colors: true
        }));
        callback();
    });
});

gulp.task("dev", ["copy","build-dev"])

//run webpack dev server
gulp.task("dev-server", function(callback) {
    // modify some webpack config options
    var myConfig = Object.create(webpackConfig);
    myConfig.devtool = "source-map";
    myConfig.debug = true;

    // Start a webpack-dev-server
    new WebpackDevServer(webpack(myConfig), {
        contentBase: 'src',
        //publicPath: "/" + myConfig.output.publicPath,
        stats: {
            colors: true
        }
    }).listen(8080, "localhost", function(err) {
        if(err) throw new gutil.PluginError("webpack-dev-server", err);
        gutil.log("[dev-server]", "http://localhost:8080/webpack-dev-server/index.html");
    });
});

gulp.task('default', ['dev-server']);