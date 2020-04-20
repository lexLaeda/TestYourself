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

          <v-autocomplete
            v-model="postData.subject"
            :items="subjects"
            label="Предмет"
            :rules="[v => !!v || validText]"
            outlined
            required
            hide-no-data
          ></v-autocomplete>

          <v-select
            v-model="postData.mode"
            :items="question_types"
            label="Тип вопроса"
            outlined
            @change="postData.current = [0]"
          ></v-select>

          <v-card
            outlined
          >
            <v-card-title>Варианты ответов</v-card-title>
            <v-card-text>
              <template v-if="postData.mode === 'MULTI'">
                <div
                  class="d-flex flex-column mb-2"
                >
                  <v-row v-for="(element, index) in answers"
                         :key="index">
                    <v-col cols="auto" class="py-0">
                      <v-checkbox
                          v-model="postData.current"
                          :value="index"
                          :rules="[postData.current.length > 0]"
                      >
                      </v-checkbox>
                    </v-col>
                    <v-col class="py-0">
                      <v-textarea
                          v-model="answers[index]"
                          :label="'Ответ' + (index + 1)"
                          :rules="[v => !!v || validText]"
                          rows="2"
                          outlined
                          required
                      ></v-textarea>
                    </v-col>
                    <v-col cols="auto" style="width: 64px;">
                      <v-btn v-if="answers.length > 2" @click="removeAnswer(index)" fab dark small color="primary darken-2">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                  <div class="text-right">
                    <v-btn @click="addAnswer" fab dark small color="primary darken-2">
                      <v-icon>mdi-plus</v-icon>
                    </v-btn>
                  </div>
                  <div class="v-messages theme--light error--text">
                    <div class="v-messages__wrapper">
                      <transition name="message">
                        <div
                          class="v-messages__message"
                          v-if="!postData.current.length > 0">
                          {{ validVariant }}
                        </div>
                      </transition>
                    </div>
                  </div>
                </div>
              </template>
              <template v-else>
                <v-radio-group
                  class="mt-0 pt-0"
                  v-model="postData.current[0]"
                  :rules="[postData.current.length > 0 || validVariant]"
                >
                  <v-row v-for="(element, index) in answers"
                   :key="index">
                    <v-col cols="auto" class="py-5">
                      <v-radio
                        :value="index"
                      >
                      </v-radio>
                    </v-col>
                    <v-col class="py-0">
                      <v-textarea
                          v-model="answers[index]"
                          :label="'Ответ' + (index + 1)"
                          :rules="[v => !!v || validText]"
                          rows="2"
                          outlined
                          required
                      ></v-textarea>
                    </v-col>
                    <v-col cols="auto" style="width: 64px;">
                      <v-btn v-if="answers.length > 2" @click="removeAnswer(index)" fab dark small color="primary darken-2">
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                  <div class="text-right">
                    <v-btn @click="addAnswer" fab dark small color="primary darken-2">
                      <v-icon>mdi-plus</v-icon>
                    </v-btn>
                  </div>
                </v-radio-group>
              </template>
            </v-card-text>
          </v-card>

          <v-row class="mt-4">
            <v-col cols="auto">
              <v-btn large color="primary darken-2 white--text"
               :disabled="!valid"
               @click="send"
              >Сохранить</v-btn>
            </v-col>
<!--            <v-col cols="auto" class="ml-auto">-->
<!--              <v-btn large text>Отменить</v-btn>-->
<!--            </v-col>-->
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
    import api from "../../backend-api";
    export default {
    name: 'AddQuestion',

    data: () => ({
      answers: ['', ''],
      valid: true,
      validText: 'Обязательное поле',
      validVariant: 'Должен быть хотя бы один правильный вариант ответа',
      subjects: [],
      question_types: [
        {text: 'одиночный', value: 'SINGLE'},
        {text: 'множественный выбор', value: 'MULTI'}
      ],
      postData: {
        mode: 'SINGLE',
        current: [0]
      }
    }),

    methods: {
      getData() {
        api.getSubjects()
          .then(response => {
            let data = response.data;
            Object.keys(data).forEach(item => {
              this.subjects.push({
                text: data[item],
                value: item
              });
            });
          })
          .catch(error => {
            console.log(error);
          })
      },

      addAnswer() {
        this.answers.push('');
      },

      removeAnswer(index) {
        let current = this.postData.current;
        this.answers.splice(index, 1);
        let replace = function (element) {
          if (current.includes(element)) {
            current.splice(current.indexOf(element), 1);
          }
          if (current.includes(element + 1)) {
            current.splice(current.indexOf(element + 1), 1);
            current.push(element);
          }
        };
        if (current.length > 1) {
          for (let i = index; i < this.answers.length; i++) {
            replace(i);
          }
        } else {
            replace(index);
        }
      },

      send() {
        this.$refs.form.validate();
        this.postData.answers = this.answers;
        console.log('postData =>', this.postData);
      }
    },

    mounted() {
      this.getData();
    }
  };
</script>