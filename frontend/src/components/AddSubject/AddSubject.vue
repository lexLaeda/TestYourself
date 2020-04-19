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
              <v-btn large color="primary darken-2 white--text"
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
  import api from "../../backend-api";

  export default {
    name: 'AddSubject',

    data: () => ({
      valid: true,
      validText: 'Обязательное поле',
      postData: {
        num_q: 0
      }
    }),

    methods: {
      sendSubject() {
        this.$refs.form.validate();
        let postData = JSON.stringify(this.postData);
        console.log('postData =>', this.postData, postData);
        api.addSubject(postData)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });

      }
    }
  }
</script>