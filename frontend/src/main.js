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

import "./scss/main.scss";

/*Components*/

Vue.config.productionTip = false;


new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
