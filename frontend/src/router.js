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
        },
        {
            path: "/test-generator",
            name: "test-generator",
            component: () => import("./components/TestGenerator/TestGenerator")
        },
        {
            path: "/subjects",
            name: "subjects",
            component: () => import("./components/Subject/SubjectList")
        },
        {
            path: "/subjects/:id",
            name: "subject",
            component: () => import("./components/Subject/Subject")
        },
        {
            path: "/test/:id",
            name: "test",
            component: () => import("./components/Test/Test")
        }
    ]
});