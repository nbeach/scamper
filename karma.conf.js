var webpackConfigMaker = require("./webpack.configMaker");

module.exports = function(config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine'],
    files: [
      'src/test/javascript/**/*.js'
    ],
    reporters: ['progress'],
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: false,
    browsers: ['PhantomJS'],
    singleRun: true,
    preprocessors: {
      'src/test/javascript/**/*.js': ['webpack', 'sourcemap']
    },
    webpack: webpackConfigMaker({
      inlineSourceMap: true
    }),
    webpackMiddleware: {
      noInfo: true
    }
  });
};