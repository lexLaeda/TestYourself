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
  export default {
    name: 'AddSubject',

    data: () => ({
      valid: true,
      validText: 'Обязательное поле',
      postData: {}
    }),

    methods: {
      sendSubject() {
        this.$refs.form.validate();

      }
    }
  }
</script>