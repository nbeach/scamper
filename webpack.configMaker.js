var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ngAnnotatePlugin = require('ng-annotate-webpack-plugin');

function makeWebpackConfig(buildConfig) {
  var webpackConfig = {
    output: {},
    entry: {
      App: []
    },
    plugins: [],
    module: {
      loaders: []
    }
  };

  webpackConfig.entry.App.push('./src/main/javascript/EntryPoint.js');
  webpackConfig.output.path = './src/main/webapp/';
  webpackConfig.output.filename = 'bundle.js';

  webpackConfig.plugins.push(new HtmlWebpackPlugin({
    template: './src/main/html/app.html',
    inject: 'body'
  }));


  webpackConfig.module.loaders.push({
    test: /\.js?$/,
    exclude: /node_modules/,
    loader: 'babel',
    query: {
      presets: ['es2015']
    }
  });

  webpackConfig.module.loaders.push({
    test: /\.html$/,
    loader: 'html-loader'
  });

  webpackConfig.module.loaders.push({
    test: /\.less$/,
    loader: "style!css!less"
  });

  webpackConfig.module.loaders.push({
    test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/,
    loader: 'file'
  });



  if(buildConfig.runHttpServer) {
    webpackConfig.entry.App.unshift('webpack-dev-server/client?http://localhost:8090/');
    webpackConfig.devServer = {
      host: "localhost",
      port: 8090,
      proxy: {
        '*': {
          target: "http://localhost:8080/",
          bypass: function (req, res, proxyOptions) {
            if (req.headers.accept.indexOf('html') !== -1) {
              console.log('Skipping proxy for browser request.');
              return '/index.html';
            }
          }
        }
      }
    }
  }


  if(buildConfig.sourceMap) {
    webpackConfig.devtool = 'source-map';
  }

  if(buildConfig.inlineSourceMap) {
    webpackConfig.devtool = 'inline-source-map';
  }

  if(buildConfig.minify) {
    webpackConfig.plugins.push(new ngAnnotatePlugin({ add: true }));

    webpackConfig.plugins.push(new webpack.optimize.UglifyJsPlugin({
      mangle: false,
      compress: {
        warnings: false
      }
    }));
  }

  //Doesn't provide any meaningful size decrease versus the build time increase as of the time this was written.
  if(buildConfig.dedupe) {
    webpackConfig.plugins.push(new webpack.optimize.DedupePlugin());
  }

  return webpackConfig;
}

module.exports = makeWebpackConfig;