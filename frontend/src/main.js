import Vue from 'vue';
import router from "./router";
import App from './App.vue';
import vuetify from './plugins/vuetify';

import "./scss/main.scss";
import "./backend-api";

/*Components*/
import AddSubject from './components/AddSubject/AddSubject';

Vue.config.productionTip = false;

Vue.component('add-subject', AddSubject);

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
