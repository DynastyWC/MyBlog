import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/login', // 重定向到登录页
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/login.vue'),
  },
  {
    path: '/index',
    name: 'index',
    component: () => import(/* webpackChunkName: "about" */ '../views/index.vue'),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
