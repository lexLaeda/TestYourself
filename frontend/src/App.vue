<template>
  <v-app>
    <toolbar></toolbar>

    <v-content>
      <v-container class="py-10">
        <router-view></router-view>
      </v-container>
    </v-content>

    <v-footer
        color="primary darken-4"
        padless
    >
      <v-row
          justify="center"
          no-gutters
      >
        <v-col
            class="py-4 text-center white--text"
            cols="12"
        >
          <a href="https://github.com/lexLaeda" target="_blank">Aleksey Ermakov</a> &
          <a href="https://github.com/EvRusskikh" target="_blank">Evgeniia Russkikh</a>
        </v-col>
      </v-row>
    </v-footer>
  </v-app>
</template>

<script>
import Toolbar from './components/Toolbar/Toolbar';

export default {
  name: 'App',

  components: {
    Toolbar
  },
};
</script>
