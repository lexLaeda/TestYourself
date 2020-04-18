<template>
  <div>
    <v-card
        class="my-10 mx-auto"
        max-width="700"
    >
      <v-card-title class="display-1">Добавить предмет</v-card-title>
      <v-card-text>
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >
          <v-text-field
              v-model="postData.name"
              label="Название"
              :rules="[v => !!v || validText]"
              outlined
              required
          ></v-text-field>

          <v-textarea
              v-model="postData.description"
              label="Описание"
              outlined
          ></v-textarea>

          <v-row class="mt-4">
            <v-col cols="auto">
              <v-btn large color="cyan darken-1 white--text"
                     :disabled="!valid"
                     @click="sendSubject"
              >Сохранить</v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
  import axios from 'axios';
  import { objectToFormData } from "object-to-formdata";

  export default {
    name: 'AddSubject',

    data: () => ({
      valid: false,
      validText: 'Обязательное поле',
      postData: {
        num_q: 0
      }
    }),

    methods: {
      sendSubject() {
        this.$refs.form.validate();
        let postData = JSON.stringify(this.postData);
        let postData2 = objectToFormData(this.postData);
        console.log('postData =>', this.postData, postData, postData2);
        axios.post('http://localhost:8098/api/subjects/add', postData)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
//         let xhr = new XMLHttpRequest();
//         xhr.open("POST", 'http://localhost:8098/api/subjects/add', true)
//
// // Отсылаем объект в формате JSON и с Content-Type application/json
// // Сервер должен уметь такой Content-Type принимать и раскодировать
//         xhr.send(postData);

      }
    }
  }
</script>