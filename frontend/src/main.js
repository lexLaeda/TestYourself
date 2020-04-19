import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import vuetify from './plugins/vuetify';

import "./scss/main.scss";

/*Components*/
import AddSubject from './components/AddSubject/AddSubject';

Vue.config.productionTip = false

Vue.component('add-subject', AddSubject);

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
