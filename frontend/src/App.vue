<template>
  <v-app>
    <v-content>
      <v-container class="py-10 fill-height flex-column align-stretch">
        <router-view></router-view>
      </v-container>
    </v-content>

    <v-footer
        color="primary darken-4"
        padless
    >
    </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'App',
};
</script>
