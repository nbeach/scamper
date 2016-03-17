var webpack = require('webpack');

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
  webpackConfig.output.path = './src/main/webapp/scripts/responsive/';
  webpackConfig.output.filename = 'bundle.js';

  webpackConfig.module.loaders.push({
    test: /\.js$/,
    exclude: /node_modules/,
    loader: 'babel'
  });

  webpackConfig.module.loaders.push({
    test: /\.html$/,
    loader: 'dust-loader'
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