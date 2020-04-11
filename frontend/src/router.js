import Hello from '@/components/Hello'

Vue.use(Router);

const router = new Router({
    ode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [{ path: '/', component: Hello }]
});
export default router;