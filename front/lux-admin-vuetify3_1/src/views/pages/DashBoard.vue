<script lang="ts" setup>
import SalesCard from "@/components/dashboard/SalesCard.vue";
import ActivityCard from "@/components/dashboard/ActivityCard.vue";
import TicketsCard from "@/components/dashboard/TicketsCard.vue";
// import TrackCard from "@/components/dashboard/TrackCard.vue";
import TodoCard from "@/components/dashboard/TodoCard.vue";
</script>
<template>
  <div class="pa-5">
    <!-- ---------------------------------------------- -->
    <!---First Row -->
    <!-- ---------------------------------------------- -->
    <v-row class="flex-0" dense>
      <v-col cols="12" xl="4">
        <!-- Sales Card -->
        <v-card class="card-shadow" height="420">
          <!-- <sales-card></sales-card> -->
          <sales-card
            :action-label="$t('dashboard.viewReport')"
            :percentage="3.2"
            :percentage-label="$t('dashboard.lastweek')"
            :value="1837.32"
            class="h-100"
            style="min-height: 380px"
          ></sales-card>
        </v-card>
      </v-col>
      <v-col cols="12" md="6" xl="4">
        <!-- Activity Card -->
        <v-card class="card-shadow" height="420">
          <activity-card></activity-card>
        </v-card>
      </v-col>
      <v-col cols="12" md="6" xl="4">
        <!-- Trafiic Card-->
        <v-card class="card-shadow h-full" height="420">

        </v-card>
      </v-col>
    </v-row>
    <!-- ---------------------------------------------- -->
    <!---Second Row -->

    <!-- ---------------------------------------------- -->
    <!---Third Row -->
    <!-- ---------------------------------------------- -->
    <v-row class="card-shadow flex-grow-0" dense>
      <v-col cols="12" xl="6">
        <!-- Target Card-->
        <v-card class="card-shadow" height="420"
        >
          <todo-card></todo-card
          >
        </v-card>
      </v-col>
      <v-col cols="12" xl="6">
        <v-card class="card-shadow" height="420"
        >
          <tickets-card></tickets-card>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>
<style lang="scss" scoped></style>
