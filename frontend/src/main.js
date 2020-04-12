import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';

import "./scss/main.scss";

/*Components*/
import AddQuestion from './components/AddQuestion/AddQuestion';

Vue.config.productionTip = false

Vue.component('add-question', AddQuestion);

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
