import "styles.css";

var App = require("sjs/demo-fastopt.js");
App.Demo.start();

if (module.hot) {
  module.hot.accept();
  App.SUIMain.main();
}
