<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col class="text-center">
          <a href="http://localhost:8098/api/hello">Я езмь ссылка</a>
          <v-btn color="primary" @click="getHello()">Click me</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <h2 class="text-center" v-if="hello.length">{{ hello }}</h2>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import api from "./backend-api";
export default {
  name: 'App',

  data: () => ({
    hello: ''
  }),

  methods: {
    getHello () {
      api.hello().then(response => {
        this.hello = response.data;
      })
              .catch(e => {
                this.errors.push(e)
              })
    }
  }
};
</script>
