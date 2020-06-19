<template>
  <div>
    Hello: {{hello}}
  </div>
</template>

<script>

  export default {
    name: 'Home',
    data: () => ({
      hello: ''
    }),
    methods: {
      getData() {
        this.$axios.get(`/hello`).then(response => {
          if (response.status === 200) {
            let data = response.data;
            this.hello = data;
          }
        })
        .catch(error => {
          console.log(error);
        });
      }
    },

    mounted() {
      this.getData();
    }
  }
</script>