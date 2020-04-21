<template>
  <div>
    <template v-if="dataLoad">
      <h1 class="display-1 mb-6"> {{ test.name }} </h1>
      <v-form>
        <div class="mt-6" v-for="(question, index) in questions" :key="index">
          <v-divider class="mb-4" v-if="index !== 0"></v-divider>
          <div class="title mb-3">{{question.name}}</div>
          <div v-if="question.description" class="mt-2">{{ question.description }}</div>
          <template v-if="question.mode === 'SINGLE'">
            <v-radio-group v-model="question.id">
              <v-radio
                class="mb-4"
                v-for="(answer, index) in question.answers" :key="index"
                :label="answer.title"
                :value="answer.number"
              >
              </v-radio>
            </v-radio-group>
          </template>
          <template v-if="question.mode === 'MULTI'">
            <v-checkbox
              v-for="(answer, index) in question.answers" :key="index"
              :label="answer.title"
              :value="answer.number"
              hide-details="true"
            >
            </v-checkbox>
          </template>
        </div>
        <v-btn large color="primary darken-2 white--text"
        >Завершить</v-btn>
      </v-form>
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