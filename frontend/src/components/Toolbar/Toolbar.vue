<template>
  <div>
    <v-app-bar
        color="cyan darken-1"
        dense
        dark
        class="flex-grow-0"
    >
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>
        <router-link class="white--text" to="/">TestYourself</router-link>
      </v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
    >
      <v-list
          nav
          dense
      >
        <v-list-item-group
            active-class="cyan--text text--darken-2"
        >
          <v-list-item v-for="(element, index) in menuList" :key="index" :to="element.path" link>
            <v-list-item-title
            >
              {{ element.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
  export default {
    name: 'Toolbar',
    data: () => ({
      drawer: false,
      menuList: [
        {
          title: 'Добавить предмет',
          path: '/add-subject'
        },
        {
          title: 'Добавить вопрос',
          path: '/add-question'
        }
      ]
    })
  }
</script>