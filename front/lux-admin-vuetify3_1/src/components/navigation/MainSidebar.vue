
<script lang="ts" setup>
import configs from "@/configs";
import MainMenu from "@/components/navigation/MainMenu.vue";
import {useCustomizeThemeStore} from "@/stores/customizeTheme";

const customizeTheme = useCustomizeThemeStore();
const navigation = ref(configs.navigation);

const openGithubSite = () => {
  window.open("https://github.com/CCP101/KidsAttentionProject2023", "_blank");
};

onMounted(() => {
  scrollToBottom();
});

const scrollToBottom = () => {
  const contentArea = document.querySelector(".v-navigation-drawer__content");
  const activeItem = document.querySelector(
    ".v-list-item--active"
  ) as HTMLElement;

  setTimeout(() => {
    contentArea?.scrollTo({
      top: activeItem?.offsetTop,
    });
  }, 100);
};
</script>

<template>
  <v-navigation-drawer
    id="mainMenu"
    v-model="customizeTheme.mainSidebar"
    border="none"
    elevation="1"
  >
    <!-- ---------------------------------------------- -->
    <!---Top Area -->
    <!-- ---------------------------------------------- -->
    <template v-if="!customizeTheme.miniSidebar" v-slot:prepend>
      <v-card
        class="d-flex align-center justify-center"
        height="100"
        style="box-shadow: rgba(0, 0, 0, 0.05) 0px 25px 15px -20px"
      >
        <img
          v-if="customizeTheme.darkTheme"
          alt=""
          src="@/assets/logo_dark.svg"
          width="200"
        />
        <img
          v-else="customizeTheme.darkTheme"
          alt=""
          src="@/assets/logo_茁跃_light.png"
          width="200"
        />
      </v-card>
    </template>

    <!-- ---------------------------------------------- -->
    <!---Nav List -->
    <!-- ---------------------------------------------- -->

    <main-menu :menu="navigation.menu"></main-menu>

    <!-- ---------------------------------------------- -->
    <!---Bottom Area -->
    <!-- ---------------------------------------------- -->
    <template v-if="!customizeTheme.miniSidebar" v-slot:append>
      <v-card
        class="pa-3"
        height="75"
        style="box-shadow: rgba(0, 0, 0, 0.05) 0px -25px 15px -20px"
        theme="dark"
        variant="text"
      >
        <v-card
          :class="customizeTheme.primaryColor.colorName"
          class="d-flex flex-column gradient pa-2"
          height="70"
        >
          <v-card-subtitle></v-card-subtitle>

          <v-card-actions>
            <v-btn
              block
              color="white"
              prepend-icon="mdi-thumb-up-outline"
              variant="elevated"
              @click="openGithubSite"
            >
              问题上报
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-card>
    </template>
  </v-navigation-drawer>
</template>

<style lang="scss" scoped></style>
