import menuPages from "./menus/pages.menu";


export default {
  menu: [
    {
      text: "",
      key: "",
      items: [
        {
          key: "menu.dashboard",
          text: "今日战况",
          link: "/dashboard",
          icon: "mdi-view-dashboard-outline",
        },
      ],
    },
    {
      text: "信息管理",
      key: "menu.pages",
      items: menuPages,
    },
  ],
};
