<template>
  <div>
    <template v-if="dataLoad">
      <h1 class="display-1 mb-6"> {{ subject.name }} </h1>
      <p v-if="subject.description"> {{ subject.description }} </p>

      <div class="mt-8">
        <template v-if="questions && questions.length > 0">
          <form>
            <v-text-field
                v-model="filter"
                label="Название вопроса"
                outlined
                append-icon="mdi-magnify"
                @input="filterList"
                hide-details
            ></v-text-field>
          </form>
          <template v-if="filteredQuestions.length > 0">
            <div class="mt-6" v-for="(question, index) in filteredQuestions" :key="index">
              <v-card
                class="mx-auto pa-6 elevation-6"
              >
                <div class="d-flex flex-nowrap justify-space-between">
                  <div class="title">{{ question.name }}</div>
                  <v-menu bottom left>
                    <template v-slot:activator="{ on }">
                      <v-btn
                          icon
                          v-on="on"
                      >
                        <v-icon>mdi-dots-vertical</v-icon>
                      </v-btn>
                    </template>

                    <v-list>
                      <v-list-item>
                        <v-list-item-title>Редактировать</v-list-item-title>
                      </v-list-item>
                      <v-list-item>
                        <v-list-item-title>Удалить</v-list-item-title>
                      </v-list-item>
                    </v-list>
                  </v-menu>
                </div>
                <div v-if="question.description" class="mt-3">{{ question.description }}</div>
              </v-card>
            </div>
          </template>
          <template v-else>
            <v-alert
                color="red lighten-2"
                dark
            >
              Вопросы не найдены
            </v-alert>
          </template>
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
  export default {
    name: "Subject",

    data: () => ({
      dataLoad: false,
      subject: {},
      questions: [],
      filter: '',
      filteredQuestions: []
    }),

    methods: {
      getData() {
        this.$axios.get(`/subjects/${Number(this.$route.params.id)}`)
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
        this.$axios.get(`/questions/subject?sub_id=${id}`)
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.questions = data;
              this.filteredQuestions = this.questions;

              this.dataLoad = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
      },

      filterList() {
        if (this.filter !== '') {
          this.filteredQuestions = this.questions
            .filter(question => question.name.toLowerCase().includes(this.filter.toLowerCase()));
        } else {
          this.filteredQuestions = this.questions;
        }
      }
    },

    mounted() {
      this.getData();
    }
  }
</script>