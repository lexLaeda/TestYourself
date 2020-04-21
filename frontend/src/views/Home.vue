<template>
  <div>
    <test-generator></test-generator>
<!--    <v-btn @click="hello">Hello</v-btn>-->
  </div>
</template>

<script>
  import api from "../backend-api";
  import TestGenerator from "../components/TestGenerator/TestGenerator";

  export default {
    name: 'Home',

    components: {
      TestGenerator
    },

    methods: {
      hello() {
        api.hello().then(response => {
          console.log('DATA =>', response.data);
        })
          .catch(error => {
            console.log('ERROR =>', error);
          })
      }
    }
  }
</script>