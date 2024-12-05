<script setup lang="ts">
import {Vue3Lottie} from "vue3-lottie";
const props = defineProps({
  size: {
    type: Number,
    default: 500,
  },
});
</script>

<template>
  <Vue3Lottie
    :height="props.size"
    :width="props.size"
    animationLink="https://assets6.lottiefiles.com/packages/lf20_ofa3xwo7.json"
  />
</template>

<style scoped lang="scss">

</style>
