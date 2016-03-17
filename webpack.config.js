var makeWebpackConfig = require("./webpack.configMaker");

var config = {
  minify: true,
  sourceMap: true,
  dedupe: true
};

module.exports = makeWebpackConfig(config);