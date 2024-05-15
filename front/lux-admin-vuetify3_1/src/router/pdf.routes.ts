export default [
  {
    path: "/pdf",
    name: "报告查看",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/pdf/pdf.vue"
        ),
    meta:{
      transition:'fade'
    }
  },
]
