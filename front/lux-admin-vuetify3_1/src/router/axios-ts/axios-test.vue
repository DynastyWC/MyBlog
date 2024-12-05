<template>
  <div>
    <button @click="getData">GET Data</button>
    <button @click="postData">POST Data</button>
  </div>
</template>

<script>
// 导入你的 Request 类
import {checkEmp9601, saveEmp9601} from './axios-request.ts';

export default {
  methods: {
    async getData() {
      checkEmp9601().then((res) => {
        console.log(res);
      });
    },
    async postData() {
      saveEmp9601().then((res) => {
        console.log(res);
      });
    },
  }
};
</script>
