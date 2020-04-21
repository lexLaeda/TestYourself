<template>
  <div>
    <h1 class="display-1 mb-6">Предметы</h1>
    <template v-if="dataLoad">
      <template v-if="subjects && subjects.length > 0">
        <v-row>
          <v-col cols="12" sm="6" v-for="(subject, index) in subjects" :key="index">
            <router-link class="ty-hover-box" :to="'/subjects/' + subject.id.toString()">
            <v-hover>
              <template v-slot="{ hover }">
                <v-card
                    :elevation="hover ? 24 : 6"
                    class="mx-auto pa-6"
                >
                  <div class="title mb-3">{{ subject.name }}</div>
                  <div v-if="subject.description" class="mt-2">{{ subject.description }}</div>
                  <div class="mt-2 caption">{{subject.amount}} вопросов</div>
                </v-card>
              </template>
            </v-hover>
            </router-link>
          </v-col>
        </v-row>
<!--        <v-btn-->
<!--            fixed-->
<!--            dark-->
<!--            fab-->
<!--            bottom-->
<!--            right-->
<!--            color="pink"-->
<!--        >-->
<!--          <v-icon>mdi-plus</v-icon>-->
<!--        </v-btn>-->
      </template>
      <template v-else>
        <v-alert
            color="red lighten-2"
            dark
        >
          Предметы не добавлены
        </v-alert>
      </template>
    </template>
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
      subjects: [],
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