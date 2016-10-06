function requireAll(r) {
  r.keys().forEach(r);
}

require('../less/global.less');

requireAll(require.context('./controller/', true, /\.js$/));
requireAll(require.context('./directive/', true, /\.js$/));
requireAll(require.context('./filter/', true, /\.js$/));
requireAll(require.context('./persistence/', true, /\.js$/));
requireAll(require.context('./service/', true, /\.js$/));