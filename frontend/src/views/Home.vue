<template>
  <div>
    <subject-list></subject-list>
  </div>
</template>

<script>
  import SubjectList from "../components/SubjectList/SubjectList";

  export default {
    name: 'Home',

    components: {
      SubjectList
    }
  }
</script>