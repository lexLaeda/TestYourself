<template>
  <div>
    <subject-list></subject-list>

    <v-form
      ref="form"
      v-model="valid"
    >
      <v-text-field
        v-model="postData.id"
        label="Subject ID"
        outlined
      ></v-text-field>
      <v-text-field
        v-model="postData.number"
        label="Subject ID"
        outlined
      ></v-text-field>
      <v-btn @click="generate">Generate</v-btn>
    </v-form>

    <br>

    <v-btn @click="hello">Hello</v-btn>

    /generate?=id="1"&quant="2"
  </div>
</template>

<script>
  import api from "../backend-api";
  import SubjectList from "../components/SubjectList/SubjectList";

  export default {
    name: 'Home',

    components: {
      SubjectList
    },

    data: () => {
      return {
        postData: {}
      }
    },

    methods: {
      hello() {
        api.hello().then(response => {
          console.log('DATA =>', response.data);
        })
          .catch(error => {
            console.log('ERROR =>', error);
          })
      },
      generate() {
        api.generateTest(`/test/generate/?id=` + this.postData.id + `&number=` + this.postData.number);
      }
    }
  }
</script>