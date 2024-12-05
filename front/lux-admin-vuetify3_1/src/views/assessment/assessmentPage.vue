
<template>
  <v-card
    variant="flat"
    height="100vh"
    class="d-flex justify-center align-center text-center"
  >

    <div>
      小朋友，你准备好了吗？
      如果准备好的话，我们就即将开始我们的测试喽！
      <robot/>
      <v-btn flat color="#00A9D7" class="mb-4 text-white" to="/assessment/test"
      >开始测试</v-btn
      >
    </div>
  </v-card>
</template>
<script>
import robot from "./component/robot.vue";
export default {
  components: {robot},
  data: () => ({
    tab: null,
  }),
}
</script>

