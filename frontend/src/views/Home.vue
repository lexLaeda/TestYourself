<template>
  <div>
    <subject-list></subject-list>

<!--    <v-btn @click="hello">Hello</v-btn>-->
  </div>
</template>

<script>
  import api from "../backend-api";
  import SubjectList from "../components/Subject/SubjectList";

  export default {
    name: 'Home',

    components: {
      SubjectList
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