<template>
  <div>
<!--    <subject-list></subject-list>-->
    <v-card
        class="my-10 mx-auto"
        max-width="700"
    >
      <v-card-text>
      <v-form
        ref="form"
      >
        <v-text-field
          v-model="postData.id"
          label="Subject ID"
          outlined
          type="number"
        ></v-text-field>
        <v-text-field
          v-model="postData.number"
          label="Number"
          outlined
          type="number"
        ></v-text-field>
        <v-btn large color="primary darken-2 white--text" @click="generate">Generate</v-btn>
      </v-form>
      </v-card-text>
    </v-card>

<!--    <v-btn @click="hello">Hello</v-btn>-->

  </div>
</template>

<script>
  import api from "../backend-api";
  // import SubjectList from "../components/SubjectList/SubjectList";

  export default {
    name: 'Home',

    components: {
      // SubjectList
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
        api.generateTest(`/test/generate?id=`+ this.postData.id + `&number=` + this.postData.number);
      }
    }
  }
</script>