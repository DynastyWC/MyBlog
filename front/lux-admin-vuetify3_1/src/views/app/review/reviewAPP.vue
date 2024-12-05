<template>
  <v-card>
    <v-tabs
      v-model="tab"
      color="deep-purple-accent-4"
      align-tabs="center"
    >
      <v-tab :value="1">专注力预约评测</v-tab>
      <v-tab :value="2">感统预约评测</v-tab>
      <v-tab :value="3">专注力已报名未评测</v-tab>
      <v-tab :value="4">感统已报名未评测</v-tab>
      <v-tab :value="5">专注力待复测成员</v-tab>
      <v-tab :value="6">感统待复测</v-tab>
    </v-tabs>
    <v-window v-model="tab">
      <v-window-item
        :key="1"
        :value="1"
      >
        <v-container fluid>
          <one/>
        </v-container>
      </v-window-item>
      <v-window-item
        :key="2"
        :value="2">
        <v-container>
          <Two/>
        </v-container>
      </v-window-item>
      <v-window-item
        :key="3"
        :value="3">
        <v-container>
          <Three/>
        </v-container>
      </v-window-item>
      <v-window-item
        :key="4"
        :value="4">
        <v-container>
          <Four/>
        </v-container>
      </v-window-item>
      <v-window-item
        :key="5"
        :value="5">
        <v-container>
          <Five/>
        </v-container>
      </v-window-item>
      <v-window-item
        :key="6"
        :value="6">
        <v-container>
          <Six/>
        </v-container>
      </v-window-item>


    </v-window>
  </v-card>
</template>
<script>
import One from "@/views/app/review/pages/one.vue";
import Two from "@/views/app/review/pages/two.vue";
import Three from "@/views/app/review/pages/three.vue";
import Four from "@/views/app/review/pages/four.vue";
import Five from "@/views/app/review/pages/five.vue";
import Six from "@/views/app/review/pages/six.vue";
export default {
  components: {Six, Five, Four, Three, Two, One},
  data: () => ({
    tab: null,
  }),
}
</script>
