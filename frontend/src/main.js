import Vue from 'vue';
import router from "./plugins/router";
import App from './App.vue';
import vuetify from './plugins/vuetify';
import axios from 'axios';

Vue.prototype.$axios = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

Vue.prototype.$axiosJson = axios.create({
  baseURL: `/api`,
  timeout: 1000,
  headers: {
    'Content-Type': 'application/json',
  }
});

Vue.prototype.$declOfNum = function (number, titles) {
  let cases = [2, 0, 1, 1, 1, 2];
  return titles[ (number%100>4 && number%100<20)? 2 : cases[(number%10<5)?number%10:5] ];
};

import "./scss/main.scss";

/*Components*/
import AddSubject from './components/AddSubject/AddSubject';

Vue.config.productionTip = false;

Vue.component('add-subject', AddSubject);

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
