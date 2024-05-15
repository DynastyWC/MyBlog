<script lang="ts" setup>
defineProps({
  value: {
    type: Number,
    default: 0,
  },
});
</script>

<template>
  <span>
    <span v-if="value === 0"> {{ value }}% </span>
    <span v-else-if="value > 0" class="text-success">
      <v-icon color="success" small>mdi-arrow-top-right</v-icon> {{ value }}%
    </span>
    <span v-else class="error--text">
      <v-icon color="error" small>mdi-arrow-bottom-right</v-icon>
      {{ Math.abs(value) }}%
    </span>
  </span>
</template>
