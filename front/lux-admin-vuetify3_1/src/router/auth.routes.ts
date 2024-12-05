export default [
  {
    path: "/auth/signin",
    name: "auth-signin",
    component: () =>
      import(
        /* webpackChunkName: "auth-signin" */ "@/views/auth/SigninPage.vue"
        ),
    meta: {
      layout: "auth",
      title: "SignIn",
    },
  },


];
