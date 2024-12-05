import unsplashRoutes from "@/views/app/unsplash/UnsplashRoutes";
import todoRoutes from "@/views/app/todo/todoRoutes";
import applicationRoutes from "@/views/app/application/applicationRoutes";
import dossierRoutes from "@/views/app/dossier/dossierRoutes";
import operational_dataRoutes from "@/views/app/operational_data/operational_dataRoutes";
import reviewRoutes from "@/views/app/review/reviewRoutes";
import saleRoutes from "@/views/app/sale/saleRoutes";
import trainingRoutes from "@/views/app/training/trainingRoutes";
import training_dataRoutes from "@/views/app/training_data/training_dataRoutes";
import training_planRoutes from "@/views/app/training_plan/training_planRoutes";
import marketRoutes from "@/views/app/market/marketRoutes";
import application_queryRoutes from "@/views/app/application_query/application_queryRoutes";
import after_applicationRoutes from "@/views/app/after_application/after_applicationRoutes";
import sale_unconnectRoutes from "@/views/app/sale_unconnect/sale_unconnectRoutes";

export default [
  {
    path: "/apps/board",
    name: "app-board",
    component: () =>
      import(
        /* webpackChunkName: "utility-board" */ "@/views/utility/BoardPage.vue"
        ),
    meta: {
      requiresAuth: true,
      title: "Board",
      layout: "ui",
      category: "APP",
    },
  },
  {
    path: "/apps/todo",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Todo",
    },
    component: () =>
      import(/* webpackChunkName: "app-todo" */ "@/views/app/todo/TodoApp.vue"),
    children: [...todoRoutes],
  },
  {
    path: "/apps/market",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "市场信息",
      title: "市场信息录入",
    },
    component: () =>
      import(/* webpackChunkName: "市场信息-市场信息录入" */ "@/views/app/market/Information_entryAPP.vue"),
    children: [...marketRoutes],
  },
  {
    path: "/apps/nitori",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Nitori",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/unsplash/UnsplashApp.vue"
        ),
    children: [...unsplashRoutes],
  },
  {
    path: "/apps/application",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Application",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/application/applicationAPP.vue"
        ),
    children: [...applicationRoutes],
  },

  {
    path: "/apps/employeeinput",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "EmployeeInput",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/application/EmployeeInputApp.vue"
        ),
    children: [...applicationRoutes],
  },
  {
    path: "/apps/application_query",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Application_query",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/application_query/application_queryAPP.vue"
        ),
    children: [...application_queryRoutes],
  },
  {
    path: "/apps/dossier",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "档案",
      title: "档案管理",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/dossier/dossierAPP.vue"
        ),
    children: [...dossierRoutes],
  },
  {
    path: "/apps/after_application",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "after_Application",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/after_application/after_applicationAPP.vue"
        ),
    children: [...after_applicationRoutes],
  },
  {
    path: "/apps/operational_data",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Operational_data",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/operational_data/operational_dataAPP.vue"
        ),
    children: [...operational_dataRoutes],
  },
  {
    path: "/apps/review",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "review",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/review/reviewAPP.vue"
        ),
    children: [...reviewRoutes],
  },
  {
    path: "/apps/sale",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Sale",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/sale/saleAPP.vue"
        ),
    children: [...saleRoutes],
  },
  {
    path: "/apps/sale_unconnect",//未联系
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Sale_Unconnect",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/sale_unconnect/sale_unconnectAPP.vue"
        ),
    children: [...sale_unconnectRoutes],
  },
  {
    path: "/apps/sale_unvisited",  //已联系未上门
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Sale_Unvisited",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/sale_unvisited/sale_unvisitedAPP.vue"
        ),
    children: [...sale_unconnectRoutes],
  },
  {
    path: "/apps/sale_unregistered",  //已上门，未报名
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Sale_Unregistered",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/sale_unregistered/sale_unregisteredAPP.vue"
        ),
    children: [...saleRoutes],
  },
  {
    path: "/apps/sale",  //其他
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Sale",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/sale/saleAPP.vue"
        ),
    children: [...saleRoutes],
  },
  {
    path: "/apps/training",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Training",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/training/trainingAPP.vue"
        ),
    children: [...trainingRoutes],
  },
  {
    path: "/apps/training_data",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Training_data",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/training_data/training_dataAPP.vue"
        ),
    children: [...training_dataRoutes],
  },
  {
    path: "/apps/training_plan",
    meta: {
      requiresAuth: true,
      layout: "ui",
      category: "APP",
      title: "Training_plan",
    },

    component: () =>
      import(
        /* webpackChunkName: "app-unsplash" */ "@/views/app/training_plan/training_planAPP.vue"
        ),
    children: [...training_planRoutes],
  }
];
