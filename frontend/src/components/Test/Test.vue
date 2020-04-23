<template>
  <div class="ty-test">
    <template v-if="dataLoad">
      <h1 class="display-1 mb-6"> {{ test.name }} </h1>
      <v-form class="ty-test__form">
          <div class="ty-test__aside d-none d-sm-block">
            <ul class="ty-test__steps">
            <li v-for="(question, index) in questions" :key="index"
                class="ty-test__step"
                :class="{'active': index === questionIndex}"
                @click="changeStep(index)">
              {{question.name}}
            </li>
            </ul>
          </div>
          <div class="ty-test__body">
            <div class="ty-test__question">
              <div v-for="(question, index) in questions" :key="index">
                <template v-if="index === questionIndex">
                  <div class="title mb-3">{{question.name}}</div>
                  <div v-if="question.description" class="mt-2">{{ question.description }}</div>
                  <template v-if="question.mode === 'SINGLE'">
                    <v-radio-group
                        v-model="userAnswer[index]['answers'][0]"
                    >
                      <v-radio
                          class="mb-4"
                          v-for="(answer, index) in question.answers" :key="index"
                          :label="answer.title"
                          :value="answer.number"
                      >
                      </v-radio>
                    </v-radio-group>
                  </template>
                  <template v-if="question.mode === 'MULTI'">
                    <v-checkbox
                        v-for="(answer, key) in question.answers" :key="key"
                        :label="answer.title"
                        :value="answer.number"
                        v-model="userAnswer[index]['answers']"
                        hide-details="true"
                    >
                    </v-checkbox>
                  </template>
                </template>
              </div>
            </div>
            <div class="ty-test__btns">
              <v-row>
                <v-col cols="auto" v-if="questionIndex > 0">
                  <v-btn large color="primary white--text"
                   @click="prevStep">
                    Предыдущий
                  </v-btn>
                </v-col>
                <v-col cols="auto" v-if="questionIndex < questions.length - 1">
                  <v-btn large color="primary white--text"
                   @click="nextStep">
                    Следующий
                  </v-btn>
                </v-col>
                <v-col cols="auto" v-if="questionIndex === questions.length - 1">
                  <v-btn large color="primary darken-2 white--text"
                   @click="sendTest"
                  >Завершить</v-btn>
                </v-col>
              </v-row>
            </div>
          </div>
      </v-form>
    </template>
    <div v-if="!dataLoad" class="text-center mt-5 mb-5">
      <v-progress-circular color="grey" indeterminate size="64"></v-progress-circular>
    </div>
  </div>
</template>

<script>
  import moment from 'moment';
  import api from "../../backend-api";

  export default {
    name: 'Test',

    data: () => ({
      dataLoad: false,
      dateFormat: 'YYYY-MM-DD HH:mm:ss.SSS',
      test: {},
      questions: [],
      questionIndex: 0,
      userAnswer: [],
      postData: {}
    }),

    methods: {
      getData() {
        api.getTest(Number(this.$route.params.id))
          .then(response => {
            if (response.status === 200) {
              let data = response.data;
              // console.log(data);
              this.test = data;
              this.questions = data.questions;
              this.constructorResult();
              this.dataLoad = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
      },

      constructorResult() {
        this.questions.forEach(question => {
          this.userAnswer.push({
            questionId: question.id,
            answers: []
          });
        });

        let postData = this.postData;
        postData.testId = this.test.id;
        postData.answers = this.userAnswer;
        postData.start = moment().format(this.dateFormat);
      },

      nextStep() {
        this.questionIndex++;
      },

      prevStep() {
        this.questionIndex--;
      },

      changeStep(index) {
        this.questionIndex = index;
      },

      sendTest() {
        this.postData.end = moment().format(this.dateFormat);
        console.log('TEST-ANSWERS =>', this.postData);
      }
    },

    mounted() {
      this.getData();
    }
  }
</script>