import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
        {
            path: "/",
            name: "home",
            component: Home
        },
        {
            path: "/add-subject",
            name: "add-subject",
            component: () => import("./components/AddSubject/AddSubject")
        },
        {
            path: "/add-question",
            name: "add-question",
            component: () => import("./components/AddQuestion/AddQuestion")
        }
    ]
});