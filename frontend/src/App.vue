<template>
  <div id="app">
    <button @click="getHello()">Click me</button>
    <br>
    <h2>{{hello}}</h2>
  </div>
</template>

<script>

import api from "./backend-api";

export default {
  name: 'App',

  data() {
    return {
      hello: '',
      errors: []
    }
  },

  methods: {
    getHello () {
      api.hello().then(response => {
        this.hello = response.data.toLowerCase();
      })
      .catch(e => {
        this.errors.push(e)
      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
