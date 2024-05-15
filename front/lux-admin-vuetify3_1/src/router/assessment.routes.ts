
export default [
  {
    path: "/assessment",
    name: "评测系统",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/assessment/assessmentPage.vue"
        ),
    meta:{
      transition:'fade'
    }
  },
  {
    path: "/assessment/test",
    name: "测试",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/assessment/pages/test.vue"
        ),
    meta:{
      transition:'fade'
    }
  },
];
