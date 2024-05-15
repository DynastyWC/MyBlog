export default [
  {
    icon: "mdi-file-lock-outline",
    key: "menu.market",
    text: "市场信息",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-login",
        key: "市场信息录入",
        text: "市场信息录入",
        link: "/apps/market",
      },
    ],
  },
  {
    icon: "mdi-file-lock-outline",
    key: "menu.dossier",
    text: "档案管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-login",
        key: "menu.dossier",
        text: "档案管理",
        link: "/apps/dossier",
      },

    ],
  },
  {
    icon: "mdi-file-outline",
    key: "menu.app",
    text: "报名管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-account-multiple-plus",
        key: "menu.application",
        text: "报名申请",
        link: "/apps/application",
      },
      {
        icon: "mdi-account-search",
        key: "menu.application_query",
        text: "报名查询",
        link: "/apps/application_query",
      },
      {
        icon: "mdi-account-multiple",
        key: "menu.after_application",
        text: "报名后",
        link: "/apps/after_application",
      },
      {
        icon: "mdi-account",
        key: "menu.employeeinput",
        text: "员工信息更新录入",
        link: "/apps/employeeinput",
      },
    ],
  },
  {
    icon: "mdi-file",
    key: "menu.sum_sale",
    text: "销售管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-wrench-clock",
        key: "menu.sale",
        text: "其他",
        link: "/apps/sale",
      },
      {
        icon: "mdi-file-cog-outline",
        key: "menu.sale_unconnected",
        text: "未联系",
        link: "/apps/sale_unconnect",
      },      {
        icon: "mdi-wrench-outline",
        key: "menu.sale_unvisited",
        text: "已联系未上门",
        link: "/apps/sale_unvisited",
      },      {
        icon: "mdi-clock-outline",
        key: "menu.sale_unregistered",
        text: "已上门未报名",
        link: "/apps/sale_unregistered",
      },
    ],
  },
  {
    icon: "mdi-file-lock-outline",
    key: "menu.review",
    text: "评测管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-login",
        key: "menu.review",
        text: "评测管理",
        link: "/apps/review",
      },

    ],
  },
  {
    icon: "mdi-file-lock-outline",
    key: "menu.training_plan",
    text: "训练计划管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-login",
        key: "menu.training_plan",
        text: "训练计划管理",
        link: "/apps/training_plan",
      },

    ],
  },
  {
    icon: "mdi-file-lock-outline",
    key: "menu.training",
    text: "训练管理",
    regex: /^\/app/,
    items: [
      {
        icon: "mdi-login",
        key: "menu.training",
        text: "训练管理",
        link: "/apps/training",
      },

    ],
  },
  {
    icon: "mdi-file-lock-outline",
    key: "menu.training_data",
    text: "训练数据分析",
    regex: /^\/app/,
    items: [

      {
        icon: "mdi-file-outline",
        key: "menu.training_data",
        text: "训练数据分析",
        link: "/apps/training_data",
      },
    ],
  },
];
