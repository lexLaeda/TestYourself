<template>
  <v-app>
    <v-app-bar
        color="cyan darken-1"
        dense
        dark
        class="flex-grow-0"
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>TestYourself</v-toolbar-title>
    </v-app-bar>

    <v-container>
      <add-question></add-question>
    </v-container>

    <v-footer
        color="cyan darken-4"
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
export default {
  name: 'App',

  data: () => ({

  }),
};
</script>
