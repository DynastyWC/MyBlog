<template>
  <div>
    <button @click="getData">获取数据</button>
    <div v-if = "flag">{{responseData}}</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      responseData: null,
      flag: false
    };
  },
  methods: {
    getData() {
      axios.get("http://localhost:8081/hello").then(res => {
        this.flag = true
        this.responseData = res.data
        console.log(res.data);
      });
    },
  },
};
</script>
