<template>
  <div>
    <v-card
        class="mx-auto"
        max-width="700"
    >
      <v-card-title class="display-1">Сгенерировать тест</v-card-title>
      <v-card-text>
        <v-form
          ref="form"
          v-model="valid"
        >
          <v-autocomplete
              v-model="postData.subject"
              :items="subjects"
              label="Предмет"
              :rules="[v => !!v || validText]"
              outlined
              required
              hide-no-data
          ></v-autocomplete>

          <v-text-field
              v-model="postData.number"
              label="Число вопросов"
              outlined
              type="number"
              required
              :rules="[v => !!v || validText]"
          ></v-text-field>

          <v-btn large color="primary darken-2 white--text"
                 :disabled="!valid"
                 @click="generateTest"
          >Начать тест</v-btn>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
  export default {
    name: 'TestGenerator',

    data: () => {
      return {
        postData: {},
        valid: true,
        validText: 'Обязательное поле',
        subjects: []
      }
    },

    methods: {
      getData() {
        this.$axios.get(`/subjects/map`)
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              Object.keys(data).forEach(item => {
                this.subjects.push({
                  text: data[item],
                  value: Number(item)
                });
              });
            }
          })
          .catch(error => {
            console.log(error);
          });
      },

      generateTest() {
        this.$refs.form.validate();
        console.log('TEST-GENERATE =>', this.postData);
        this.$axios.get(`/generator/test?subjectId=${this.postData.subject}&size=${this.postData.number}`)
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              this.$router.push(`/subjectTest/${data.id}`);
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