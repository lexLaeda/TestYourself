<template>
  <div>
    <v-app-bar
        color="primary darken-1"
        dense
        dark
        class="flex-grow-0"
    >
      <v-btn v-if="backArrow" icon>
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>

      <v-app-bar-nav-icon v-else @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>
        <router-link class="white--text" to="/">TestYourself</router-link>
      </v-toolbar-title>

      <v-spacer></v-spacer>

    </v-app-bar>

    <v-navigation-drawer
        v-model="drawer"
        fixed
        temporary
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/women/21.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ user.name }}</v-list-item-title>
            <v-list-item-subtitle>{{ user.status }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>
      </template>

      <v-list
          nav
          dense
      >
        <v-list-item-group
            active-class="primary--text text--darken-2"
            v-for="(menu, index) in [authorMenuList, menuList]"
            :key="index"
        >
          <v-list-item v-for="(element, index) in menu" :key="index" :to="element.path" link>
            <v-list-item-icon v-if="element.icon">
              <v-icon>{{ element.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title
              >
                {{ element.title }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider class="my-2"></v-divider>
        </v-list-item-group>
      </v-list>

      <template v-slot:append>
        <v-divider></v-divider>
        <div class="pa-2">
          <v-btn block color="primary darken-2 white--text">Выйти</v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
  export default {
    name: 'Toolbar',
    data: () => ({
      drawer: false,
      user: {
        name: 'Маргошенька',
        status: 'автор'
      },
      authorMenuList: [
        {
          title: 'Предметы',
          path: '/subjects',
          icon: 'mdi-bookshelf'
        },
        {
          title: 'Добавить предмет',
          path: '/add-subject',
          icon: 'mdi-plus'
        },
        {
          title: 'Добавить вопрос',
          path: '/add-question',
          icon: 'mdi-plus'
        },
        {
          title: 'Сгенерировать тест',
          path: '/test-generator',
          icon: 'mdi-format-list-checkbox'
        }
      ],
      menuList: [
        {
          title: 'Настройки',
          path: '/settings',
          icon: 'mdi-tune'
        }
      ]
    }),
    props: {
      backArrow: {
        type: Boolean,
        default: false
      }
    }
  }
</script>