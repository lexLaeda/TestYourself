<template>
  <div>
    <v-card
        class="my-10 mx-auto"
        max-width="700"
    >
      <v-card-title class="display-1">Добавить вопрос</v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field
              label="Название"
              outlined
              required
          ></v-text-field>

          <v-textarea
              label="Текст вопроса"
              outlined
          ></v-textarea>

          <v-combobox
              :items="subjects"
              label="Предмет"
              outlined
              clearable
          ></v-combobox>

          <v-select
              v-model="postData.type"
              :items="question_types"
              label="Тип вопроса"
              outlined
          ></v-select>

          <v-card
              outlined
          >
            <v-card-title>Варианты ответов</v-card-title>
            <v-card-text>
              <template v-if="postData.type === 'checkbox'">222</template>
              <template v-else>111</template>
            </v-card-text>
          </v-card>

          <v-row class="mt-4">
            <v-col cols="auto">
              <v-btn large dark color="cyan darken-1">Сохранить</v-btn>
            </v-col>
            <v-col cols="auto">
              <v-btn large dark color="cyan darken-1">Сохранить и добавить новый</v-btn>
            </v-col>
            <v-col cols="auto" class="ml-auto">
              <v-btn large text>Отменить</v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
  export default {
    name: 'AddQuestion',

    data: () => ({
      postData: {},
      question_types: [
        {text: 'одиночный', value: 'radio'},
        {text: 'множественный выбор', value: 'checkbox'}
      ],
      subjects: ['Java', 'JavaScript', 'Docker', 'как испечь пирожок']
    }),
  };
</script>