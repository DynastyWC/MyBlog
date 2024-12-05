<script lang="ts" setup>
import {useTheme} from "vuetify";
import {useCustomizeThemeStore} from "@/stores/customizeTheme";
import {Icon} from "@iconify/vue";

interface Color {
  colorId: number;
  colorName: string;
  colorValue: string;
}

const customizeTheme = useCustomizeThemeStore();
const theme = useTheme();
const themeDrawer = ref(false);
const currentColor = ref<Color>({
  colorId: 2,
  colorName: "grey",
  colorValue: "#344767",
});
const primaryColors = ref([
  {
    colorId: 1,
    colorName: "purple",
    colorValue: "#CB0C9F",
  },
  {
    colorId: 2,
    colorName: "grey",
    colorValue: "#344767",
  },
  {
    colorId: 3,
    colorName: "info",
    colorValue: "#17C1E8",
  },
  {
    colorId: 4,
    colorName: "success",
    colorValue: "#82D616",
  },
  {
    colorId: 5,
    colorName: "warning",
    colorValue: "#F2825A",
  },
  {
    colorId: 6,
    colorName: "error",
    colorValue: "#EA0606",
  },
]);

onMounted(() => updatePrimaryColor(customizeTheme.primaryColor));

watch(currentColor, (newVal) => {
  updatePrimaryColor(newVal)
});

const updatePrimaryColor = (newColor: Color) => {
  theme.themes.value.light.colors.primary = newColor.colorValue;
  theme.themes.value.dark.colors.primary = newColor.colorValue;
  customizeTheme.setPrimaryColor(newColor);
  currentColor.value = newColor;

}
</script>

<template>
  <div>
    <div class="drawer-button" @click="themeDrawer = true">
      <v-icon class="text-white">mdi-cog-outline</v-icon>
    </div>

    <v-navigation-drawer
      v-model="themeDrawer"
      class="theme-drawer"
      location="right"
      temporary
      width="300"
    >
      <div class="pa-5">
        <div class="top-area">
          <div class="d-flex align-center">
            <b>UI 设置</b>
            <v-spacer></v-spacer>
          </div>
          <div>See our dashboard options.</div>
        </div>

        <hr class="my-6"/>

        <div class="theme-area">
          <b>切换主题</b>
          <div v-if="customizeTheme.darkTheme" class="px-3 pt-3">
            <v-btn
              class="text-white"
              color="grey-darken-4"
              icon
              @click="customizeTheme.darkTheme = !customizeTheme.darkTheme"
            >
              <Icon icon="line-md:moon-filled-loop" width="30"/>
            </v-btn>
            <span class="ml-5">暗黑模式</span>
          </div>
          <div v-else class="px-3 pt-3">
            <v-btn
              class="text-red"
              color="white"
              icon
              @click="customizeTheme.darkTheme = !customizeTheme.darkTheme"
            >
              <Icon
                icon="line-md:moon-filled-alt-to-sunny-filled-loop-transition"
                width="30"
              />
            </v-btn>
            <span class="ml-5">明亮模式</span>
          </div>
        </div>
        <hr class="my-6"/>

        <div class="primary-color-area">
          <b>主体颜色</b>
          <v-item-group
            v-model="currentColor"
            class="mt-3"
            mandatory
            selected-class="elevation-12"
          >
            <v-item
              v-for="color in primaryColors"
              :key="color.colorId"
              v-slot="{ isSelected, toggle }"
              :value="color"
            >
              <v-btn
                :color="color.colorValue"
                class="text-white mr-1"
                icon
                size="30"
                @click="toggle"
              >
                <Icon v-if="isSelected" icon="line-md:confirm" width="22"/>
              </v-btn>
            </v-item>
          </v-item-group>
        </div>
        <hr class="my-6"/>
        <div class="">
          <b>缩小边框</b>
          <v-switch
            :label="`Mini: ${customizeTheme.miniSidebar}`"
            class="ml-2"
            color="primary"
            hide-details
          ></v-switch>
        </div>
        <hr class="mb-6"/>
        
      </div>
    </v-navigation-drawer>
  </div>
</template>

<style lang="scss" scoped>
.drawer-button {
  position: fixed;
  background-color: #705cf6;
  top: 340px;
  right: -45px;
  z-index: 999;
  padding: 0.5rem 1rem;
  border-top-left-radius: 0.5rem;
  border-bottom-left-radius: 0.5rem;
  box-shadow: 1px 1px 9px #705cf6;
  transition: all 0.5s;
  cursor: pointer;

  &:hover {
    box-shadow: 1px 1px 18px #705cf6;
    right: 0px;
    transition: all 0.5s;
  }

  .v-icon {
    font-size: 1.3rem;
    animation: rotation 1s linear infinite;
  }

  @keyframes rotation {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
}

hr {
  background-image: linear-gradient(
      90deg,
      transparent,
      rgba(0, 0, 0, 0.4),
      transparent
  ) !important;
  background-color: transparent;
  opacity: 0.25;
  border: none;
  height: 1px;
}
</style>
