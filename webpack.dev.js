var makeWebpackConfig = require("./webpack.configMaker");

var config = {
  sourceMap: true
  //runHttpServer: true
};

module.exports = makeWebpackConfig(config);