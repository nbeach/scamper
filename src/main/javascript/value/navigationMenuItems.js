import angularApp from '../AngularApp';

var navigationMenuItems = [
  {
    "title":"Overview",
    "glyph":"glyphicon-stats",
    "path":"/overview",
    "children":null
  },
  {
    "title":"Transactions",
    "glyph":"glyphicon-list",
    "path":"/transaction",
    "children":null
  },
  {
    "title":"Import",
    "glyph":"glyphicon-cloud-upload",
    "path": "/import",
    "children":null
  }
];

angularApp.value('navigationMenuItems', navigationMenuItems);
export default navigationMenuItems;