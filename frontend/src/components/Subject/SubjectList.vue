<template>
  <div>
    <h1 class="display-1 mb-6">Предметы</h1>
    <template v-if="dataLoad">
      <template v-if="subjects && subjects.length > 0">
        <form class="mb-6">
          <v-text-field
              v-model="filter"
              label="Название предмета"
              outlined
              append-icon="mdi-magnify"
              @input="filterList"
              hide-details
          ></v-text-field>
        </form>
        <v-row v-if="filteredSubjects.length > 0">
          <v-col cols="12" sm="6" v-for="(subject, index) in filteredSubjects" :key="index">
            <v-card
              class="mx-auto pa-6 elevation-6"
            >
              <div class="d-flex flex-nowrap justify-space-between mb-3">
                <div class="title">{{ subject.name }}</div>
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
              <div v-if="subject.description" class="mt-2">{{ subject.description }}</div>
              <router-link class="mt-2 caption" :to="`/subjects/${subject.id.toString()}`">
                {{subject.amountQuestions}}
                {{$declOfNum(subject.amountQuestions, ['вопрос', 'вопроса', 'вопросов'])}}
              </router-link>
            </v-card>
          </v-col>
        </v-row>
        <template v-else>
          <v-alert
              color="red lighten-2"
              dark
          >
            Предметы не найдены
          </v-alert>
        </template>
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
  export default {
    name: 'SubjectList',

    data: () => ({
      dataLoad: false,
      subjects: [],
      filter: '',
      filteredSubjects: []
    }),

    methods: {
      getData() {
        this.$axios.get(`/subjects/all`)
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.subjects = data;
              this.filteredSubjects = this.subjects;

              this.dataLoad = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
      },

      filterList() {
        if (this.filter !== '') {
          this.filteredSubjects = this.subjects
            .filter(subject => subject.name.toLowerCase().includes(this.filter.toLowerCase()));
        } else {
          this.filteredSubjects = this.subjects;
        }
      }
    },

    mounted() {
      this.getData();
    }
  }
</script>