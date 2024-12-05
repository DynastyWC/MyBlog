<script lang="ts" setup>
import {useUnsplashStore} from "../unsplashStore";
import {Collection} from "../unsplashTypes";

const unsplashStore = useUnsplashStore();
const props = defineProps<{
  collection: Collection;
}>();

const snackbar = reactive({
  isShow: false,
  timeout: 1000,
  text: "",
});
</script>

<template>
  <div class="">
    <v-card class="collection-card shadow-1">
      <router-link :to="`/apps/unsplash/collection/${collection.id}`">
        <div class="card-top">
          <v-row no-gutters>
            <v-col cols="8">
              <v-img
                :lazy-src="collection.cover_photo.urls.small"
                :src="collection.cover_photo.urls.small"
                aspect-ratio="1/2"
                class="mr-1"
                cover
                height="304"
              ></v-img>
            </v-col>
            <v-col cols="4">
              <div class="flex-fill pb-1">
                <v-img
                  :lazy-src="collection.preview_photos[1].urls.small"
                  :src="collection.preview_photos[1].urls.small"
                  class="mb-1"
                  cover
                  height="150"
                ></v-img>
                <v-img
                  :lazy-src="collection.preview_photos[2].urls.small"
                  :src="collection.preview_photos[2].urls.small"
                  cover
                  height="150"
                ></v-img>
              </div>
            </v-col>
          </v-row>
        </div>
      </router-link>
      <v-card-title>
        <router-link :to="`/apps/unsplash/collection/${collection.id}`">
          <h4 class="font-weight-bold">{{ collection.title }}</h4>
        </router-link>
      </v-card-title>
      <v-card-subtitle>
        <span> {{ collection.total_photos }} photos.</span>
        <span class="ml-2"
        >Curated by
          <router-link :to="`/apps/unsplash/user/${collection.user.username}`">
            <b>{{ collection.user.username }}</b>
          </router-link>
        </span>
      </v-card-subtitle>

      <v-card-text>
        <div class="two-line" style="height: 100px">
          <v-chip
            v-for="item in collection.tags"
            :key="item.title"
            class="interest-chip ma-2"
            color="primary"
            label
          >
            <v-icon icon="mdi-label-outline" start></v-icon>
            {{ item.title }}
          </v-chip>
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>

<style lang="scss" scoped>
.collection-card {
  &:hover {
    box-shadow: rgba(99, 99, 99, 0.3) 0px 2px 24px 0px !important;
  }

  .card-top {
    cursor: zoom-in;
  }
}
</style>
