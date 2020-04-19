import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import light from './theme';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: { light },
  },
});
