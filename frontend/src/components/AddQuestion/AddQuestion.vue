<template>
  <div>
    <v-card
      class="my-10 mx-auto"
      max-width="700"
    >
      <v-card-title class="display-1">Добавить вопрос</v-card-title>
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
            label="Текст вопроса"
            outlined
          ></v-textarea>

          <v-combobox
            v-model="postData.subject"
            :items="subjects"
            label="Предмет"
            :rules="[v => !!v || validText]"
            outlined
            clearable
            required
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
              <template v-if="postData.type === 'checkbox'">
                <div class="d-flex flex-column">
                  <v-row v-for="(element, index) in answers"
                         :key="index">
                    <v-col cols="auto">
                      <v-checkbox
                          v-model="postData.current"
                          :value="index"
                      >
                      </v-checkbox>
                    </v-col>
                    <v-col>
                      <v-text-field
                          v-model="answers[index]"
                          :label="'Ответ' + (index + 1)"
                          :rules="[v => !!v || validText]"
                          outlined
                          required
                      ></v-text-field>
                    </v-col>
                    <v-col v-if="index > 1" cols="auto">
                      <v-btn @click="removeAnswer(index)" color="red lighten-1 white--text">Удалить</v-btn>
                    </v-col>
                  </v-row>
                  <v-btn @click="addAnswer" color="cyan darken-1 white--text">Добавить</v-btn>
                </div>
              </template>
              <template v-else>
                <v-radio-group
                    v-model="postData.current[0]">
                  <v-row v-for="(element, index) in answers"
                   :key="index">
                    <v-col cols="auto">
                      <v-radio
                        :value="index"
                      >
                      </v-radio>
                    </v-col>
                    <v-col>
                      <v-text-field
                        v-model="answers[index]"
                        :label="'Ответ' + (index + 1)"
                        :rules="[v => !!v || validText]"
                        outlined
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col v-if="index > 1" cols="auto">
                      <v-btn @click="removeAnswer(index)" color="red lighten-1 white--text">Удалить</v-btn>
                    </v-col>
                  </v-row>
                  <v-btn @click="addAnswer" color="cyan darken-1 white--text">Добавить</v-btn>
                </v-radio-group>
              </template>
            </v-card-text>
          </v-card>

          <v-row class="mt-4">
            <v-col cols="auto">
              <v-btn large color="green lighten-1 white--text"
               :disabled="!valid"
               @click="validate"
              >Сохранить</v-btn>
            </v-col>
            <v-col cols="auto">
              <v-btn large color="green lighten-1 white--text"
               :disabled="!valid"
               @click="validate"
              >Сохранить и добавить новый</v-btn>
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
      answers: ['', ''],
      valid: true,
      validText: 'Обязательное поле',
      postData: {
          type: 'radio',
          current: []
      },
      question_types: [
        {text: 'одиночный', value: 'radio'},
        {text: 'множественный выбор', value: 'checkbox'}
      ],
      subjects: ['Java', 'JavaScript', 'Docker', 'как испечь пирожок'],
    }),

    methods: {
      addAnswer() {
        this.answers.push('');
      },

      removeAnswer(index) {
        let current = this.postData.current;
        this.answers.splice(index, 1);
        if (current.includes(index)) {
          current.splice(current.indexOf(index), 1);
        }
        if (current.includes(index + 1)) {
          current.splice(current.indexOf(index + 1), 1);
          current.push(index);
        }
      },

      validate() {
        this.$refs.form.validate();
        this.postData.answers = this.answers;
        console.log('postData =>', this.postData);
      }
    }
  };
</script>