<template>
  <v-card
      class="mx-auto"
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

      <div class="mt-4">{{message}}</div>
    </v-card-text>
  </v-card>
</template>

<script>
  import api from "../../backend-api";

  export default {
    name: 'TestGenerator',

    data: () => {
      return {
        postData: {},
        message: ''
      }
    },

    methods: {
      generate() {
        api.generateTest(this.postData.id, this.postData.number)
          .then(response => {
            let data = response.data;
            console.log('TEST-GENERATOR', response);
            this.message = data;
          })
          .catch(error => {
            console.log(error);
          });
      }
    }
  }
</script>