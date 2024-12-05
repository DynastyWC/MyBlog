import {createRouter, createWebHistory} from "vue-router";
import UserRoutes from "./user.routes";
import AuthRoutes from "./auth.routes";
import UtilityRoutes from "./utility.routes";
import assessmentRutes from "./assessment.routes";
import pdfRoutes from "@/router/pdf.routes";

import AppsRoutes from "./apps.routes";


export const routes = [
  {
    path: "/",
    redirect: "/dashboard",
    meta: {},
  } as any,
  {
    path: "/dashboard",
    meta: {
      requiresAuth: true,
      layout: "landing",
    },
    component: () => import("@/views/pages/DashBoard.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    name: "error",
    component: () =>
      import(/* webpackChunkName: "error" */ "@/views/errors/NotFoundPage.vue"),
  },
  ...UserRoutes,
  ...AuthRoutes,
  ...UtilityRoutes,
  ...assessmentRutes,
  ...AppsRoutes,
  ...pdfRoutes,

];

// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [];

const router = createRouter({
  history: createWebHistory(),
  // hash模式：createWebHashHistory，history模式：createWebHistory
  // process.env.NODE_ENV === "production"

  routes: routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return {top: 0};
    }
  },
});

export default router;
