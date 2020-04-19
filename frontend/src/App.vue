<template>
  <v-app>
    <toolbar></toolbar>

    <v-content>
      <v-container>
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
          {{ new Date().getFullYear() }}
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
