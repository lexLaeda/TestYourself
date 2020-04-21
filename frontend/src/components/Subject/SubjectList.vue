<template>
  <div>
    <h1 class="display-1 mb-6">Предметы</h1>
    <div v-if="dataLoad">
      <v-row v-if="subjects && subjects.length > 0">
        <v-col cols="12" sm="6" v-for="(subject, index) in subjects" :key="index">
          <router-link class="ty-hover-box" :to="subject.name">
          <v-hover>
            <template v-slot="{ hover }">
              <v-card
                  :elevation="hover ? 24 : 6"
                  class="mx-auto pa-6"
              >
                <div class="title">{{ subject.name }}</div>
                <div v-if="subject.description" class="mt-4">{{ subject.description }}</div>
                <div v-if="subject.questions" class="mt-4 caption">{{subject.questions.length}} вопросов</div>
              </v-card>
            </template>
          </v-hover>
          </router-link>
        </v-col>
      </v-row>
      <div v-else>
        <v-alert
            color="red lighten-2"
            dark
        >
          Предметы не добавлены
        </v-alert>
      </div>
    </div>
    <div v-if="!dataLoad" class="text-center mt-5 mb-5">
      <v-progress-circular color="grey" indeterminate size="64"></v-progress-circular>
    </div>
  </div>
</template>

<script>
  import api from "../../backend-api";

  export default {
    name: 'SubjectList',

    data: () => ({
      dataLoad: false,
      subjects: []
    }),

    methods: {
      getData() {
        api.getAllSubjects()
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.subjects = data;

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