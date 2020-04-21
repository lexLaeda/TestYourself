<template>
  <div>
    <h1 class="display-1 mb-6">Предметы</h1>
    <v-row v-if="subjects && subjects.length > 0">
      <v-col cols="12" sm="6" v-for="(subject, index) in subjects" :key="index">
        <v-hover>
          <template v-slot="{ hover }">
            <v-card
                :elevation="hover ? 24 : 6"
                class="mx-auto pa-6"
            >
              <div class="title">{{ subject.name }}</div>
              <div v-if="subject.description" class="mt-4">{{ subject.description }}</div>
              <div class="mt-4 caption">{{subject.questions.length}} вопросов</div>
            </v-card>
          </template>
        </v-hover>
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
</template>

<script>
  import api from "../../backend-api";

  export default {
    name: 'SubjectList',

    data: () => ({
      subjects: []
    }),

    methods: {
      getData() {
        api.getAllSubjects()
          .then(response => {
            let data = response.data;
            this.subjects = data;
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