<template>
  <div>
    <template v-if="dataLoad">
      <h1 class="display-1 mb-6"> {{ test.name }} </h1>
      <div v-for="(question, index) in questions">
        {{question.name}}
      </div>
    </template>
    <div v-if="!dataLoad" class="text-center mt-5 mb-5">
      <v-progress-circular color="grey" indeterminate size="64"></v-progress-circular>
    </div>
  </div>
</template>

<script>
  import api from "../../backend-api";

  export default {
    name: 'Test',

    data: () => ({
      dataLoad: false,
      test: {},
      questions: []
    }),

    methods: {
      getData() {
        api.getTest(Number(this.$route.params.id))
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.test = data;
              this.questions = data.questions;
              this.dataLoad = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
      }
    },

    mounted() {
      this.getData();
    }
  }
</script>