
<template>
  <v-stepper  non-linear v-model="currentStep">
    <v-stepper-header>
      <v-stepper-item complete editable value="1">
        听力测试
      </v-stepper-item>
      <v-divider></v-divider>
      <v-stepper-item complete editable value="2">
        方格测试
      </v-stepper-item>
      <v-divider></v-divider>

      <v-stepper-item complete editable value="4">
        寻找不同
      </v-stepper-item>
      <v-divider></v-divider>
      <v-stepper-item complete editable value="5">
        特殊记忆
      </v-stepper-item>
    </v-stepper-header>
  </v-stepper>
  <template v-if=" currentStep === '1'">
    <test1></test1>
    </template>
  <template v-if=" currentStep === '2'">
    <test2></test2>
  </template>
  <template v-if=" currentStep === '4'">
    <test3></test3>
  </template>
  <template v-if=" currentStep === '5'">
    <test5></test5>
  </template>

</template>
<script lang="ts">
import robot from '../component/robot.vue'
import test1 from './test1.vue'
import test2 from './test2.vue'
import test3 from './test3.vue'
import test5 from './test5.vue'

export default {
  data() {
    return {
      tab: null,
      currentStep: this.$route.params.step || '1', // 初始步骤
    };
  },
  components:{
    robot,
    test1,
    test2,
    test3,
    test5,
  },
};
</script>


