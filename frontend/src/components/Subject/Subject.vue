<template>
  <div>
    <template v-if="dataLoad">
      <h1 class="display-1 mb-6"> {{ subject.name }} </h1>
      <p v-if="subject.description"> {{ subject.description }} </p>

      <div class="mt-8">
        <template v-if="questions && questions.length > 0">
          <div class="mt-6" v-for="(question, index) in questions" :key="index">
            <v-hover>
              <template v-slot="{ hover }">
                <v-card
                    :elevation="hover ? 24 : 6"
                    class="mx-auto pa-6"
                >
                  <div class="title">{{ question.name }}</div>
                  <div v-if="question.description" class="mt-3">{{ question.description }}</div>
                </v-card>
              </template>
            </v-hover>
          </div>
        </template>

        <template v-else>
          <v-alert
              color="red lighten-2"
              dark
          >
            Вопросы не добавлены
          </v-alert>
        </template>
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
    name: "Subject",

    data: () => ({
      dataLoad: false,
      subject: {},
      questions: []
    }),

    methods: {
      getData() {
        api.getSubject(Number(this.$route.params.id))
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.subject = data;
              this.getQuestions(this.subject.id);
            }
          })
          .catch(error => {
            console.log(error);
          });
      },

      getQuestions(id) {
        api.getQuestionBySubject(id)
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.questions = data;

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